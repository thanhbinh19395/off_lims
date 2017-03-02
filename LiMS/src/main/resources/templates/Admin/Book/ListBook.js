/**
 * Created by dylan on 2/17/2017.
 */
framework.factory('ListBook', {
    onMessageReceive: function (sender, data) {
        if(sender.pageName =='InsertBook' || sender.pageName =='UpdateBook'){
            if(data.success){
                this.onbtnReloadClick();
                sender.close();
            }
        }
    },
    onInitHeader: function (header) {

        header.setName('header');
        var self = this;
        var form = widget.setting.form();
        form.setName('searchForm')
            .setFieldPerRow(1) // so cot trong form
            .addFields([
                { field: 'name', type: 'text', required: true, caption: 'Tên sách' },
                { field: 'publish_year', type: 'text', required: true, caption: 'Năm xuất bản' },
                { field: 'author', type: 'text', required: false, caption: 'Tác Giả' },
                { field: 'publisher', type: 'text', required: false, caption: 'Nhà xuất bản' },
                { field: 'bookCategoryId', caption: 'Thể Loại', type: 'text', required: true },
                { field: 'bookStatusId', caption: 'Tình trạng', type: 'text', required: true },
                { field: 'state', caption: 'Trạng thái', type: 'text', required: true }
            ])
        ;
        header.setTitle('Danh sách Sách')
            .setIcon('fa fa-list');

        var formFooter = widget.setting.toolbar();
        formFooter.addItem({
            type: 'button', id: 'btn-search', caption: 'Tìm kiếm', icon: 'fa-search',
            onClick: self.onbtnSearchClickSearchForm.bind(self)
        });

        var formPanel = widget.setting.panel();
        formPanel.setWidth('700px').addClass('pull-right');
        formPanel.addItem(form.end());
        formPanel.addItem(formFooter.end());

        header.content().setName('headerContent').addItem(formPanel.end());
        header.title()
            .setName('title1')

            .addLeft({
                type: 'button', id: 'btn-reload', caption: 'Tải lại', icon: 'fa-refresh',
                onClick: self.onbtnReloadClick.bind(self)
            })
            .addRight({
                type: 'button', id: 'btn-search', caption: 'Tìm kiếm', icon: 'fa-search',
                onClick: function (evt) {
                    var headerContent = self.findElement('headerContent');
                    headerContent.toggle();
                    var searchForm = self.findElement('searchForm');
                    searchForm.resize();
                }
            })
        ;
    },
    onInitContent: function (content) {
        var self = this;
        var grid = widget.setting.grid();
        var pagi = widget.setting.pagination();
        console.log(this.ViewBag);
        pagi.setName('page')
            .setTotalPages(this.ViewBag.listBook.totalPage)
            .setStartPage(this.ViewBag.listBook.currentPage)
            .setPageClickHandler(self.onPageClick.bind(this))
        ;
        grid.setName('grid')
            .addColumns([
                { field: 'id', caption: 'Mã', size: '5%', sortable: true, resizable: true },
                { field: 'name', caption: 'Tên Sách', size: '30%', sortable: true, resizable: true },
                { field: 'publish_year', caption: 'Năm Xuất Bản', size: '10%', sortable: true, resizable: true },
                { field: 'author', caption: 'Tác giả', size: '10%', sortable: true, resizable: true },
                { field: 'publisher', caption: 'Nhà xuất bản', size: '30%', sortable: true, resizable: true },

                { field: 'image', caption: 'Hình', size: '15%', sortable: true, resizable: true },

                { field: 'state', caption: 'Trạng thái', size: '15%', sortable: true, resizable: true },
                { field: 'publisher', caption: 'Nhà xuất bản', size: '15%', sortable: true, resizable: true },
                { field: 'bookCode', caption: 'Book Code', size: '15%', sortable: true, resizable: true },
                { field: 'bookCategory.category_name', caption: 'Thể loại', size: '15%', sortable: true, resizable: true },
                { field: 'bookStatus.description', caption: 'Tình trạng', size: '15%', sortable: true, resizable: true },
                { field:'hinh', caption:'Hình', size:'15%', render: function(record){
                    return "<img style=\"vertical-align:center;\" height=\"60\" src=\""+ record.imageUrl +"\"><\/img>";
                }, style:'text-align:center'},
            ])
            .addButton('btnInsert', 'Thêm', 'fa fa-plus', self.onbtnInsertClickGrid.bind(this))
            .addButton('btnUpdate', 'Cập nhật', 'fa fa-pencil', self.onbtnUpdateClickGrid.bind(this))
            .addButton('btnDelete', 'Xóa', 'fa fa-trash-o', self.onbtnDeleteClickGrid.bind(this))
            .setIdColumn('id')
            .addRecords(self.ViewBag.listBook.data).setPaginateOptions(pagi.end())
        ;
        //nếu đc mở kiểu popup thì có thêm sự kiện click grid
        if (this.parentId) {
            grid.createEvent('onDblClick', self.onDblClickGrid.bind(this));
        }

        content.addItem(grid.end());
    },

    onbtnInsertClickGrid: function () {
        this.openPopup({
            name: 'insertPopup',
            url: '/Admin/Book/InsertBook',
            title: 'Insert Book',
            width: '700px'
        });
    },
    onbtnUpdateClickGrid: function () {
        var grid = this.findElement('grid');
        var id = grid.getSelection()[0];
        console.log(id);
        if (!id) {
            //thong bao = noty
            alert("vui long chon");
            return;
        }
        this.openPopup({
            name: 'updatePopup',
            url: '/Admin/Book/UpdateBook/'+id,
            title: 'Update Role',
            width: '700px'
        });

    },
    onbtnDeleteClickGrid: function () {
        var self = this;
        w2confirm('Bạn có chắc chắn muốn xóa các dòng này không ?').yes(function () {
            var grid = self.findElement('grid');
            var id = grid.getSelection()[0];
            $.post('/api/Book/Deletes', { id: id }, function (result) {
                if(result.success){
                    alertSuccess(result.message);
                    self.onbtnReloadClick();
                }
                else
                    alert(result.message)
            });
        });
    },
    onbtnSearchClickSearchForm: function (evt) {
        var form = this.findElement("searchForm");
        var grid = this.findElement('grid');
        var self = this;
        this.searchParam = form.record;
        this.reloadGridData();
        this.findElement("headerContent").toggle();
    },
    onPageClick: function (event, page) {
        var grid = this.findElement('grid');
        this.searchParam = {
            page:page,
            size : 1
        };
        this.reloadGridData();
    },
    onbtnReloadClick: function (evt) {
        var grid = this.findElement('grid');
        var form = this.findElement('searchForm');

        //clear form search + param
        this.searchParam = {};
        form.clear();

        //reload grid data
        this.reloadGridData();
    },
    reloadGridData:function(){
        var grid = this.findElement('grid');
        $.post('/api/Book/GetList',this.searchParam, function (result) {
            if(result.success){
                grid.clear();
                grid.add(result.data);
                if (grid.pagination)
                    grid.pagination.reset(result.currentPage, result.totalPage);
            }
            else{

            }
        });
    },
    onDblClickGrid: function (e) {
        var self = this;
        var grid = this.findElement('grid');
        var record = grid.get(e.recid);
        var mess = {
            type: 'popupListBook',
            data: record,
            callback: function () {
                self.close();
            }
        }
        this.sendMessage(mess);
    }
});
