/**
 * Created by dylan on 2/21/2017.
 */
framework.factory('ListBookBorrowHeader', {
    onMessageReceive: function (sender, data) {
        if(sender.pageName =='InsertBookBorrowHeader' || sender.pageName =='UpdateBookBorrowHeader'){
            if(data.success){
                this.onbtnReloadClick();
                sender.close();
            }
        }
    },
    onInitHeader: function (header) {
        console.log(this.ViewBag);
        header.setName('header');
        var self = this;
        var form = widget.setting.form();
        form.setName('searchForm')
            .setFieldPerRow(1) // so cot trong form
            .addFields([
                { field: 'bookTransaction', type: 'text', required: false, caption: "Tình trạng" },
                { field: 'returnDate', type: 'date', required: false, caption: "Ngày trả sách" },
                { field: 'status', type: 'date', required: false, caption: "Trạng thái" },
            ])
        ;
        header.setTitle('Danh sách Thể Loại')
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
            .setTotalPages(this.ViewBag.listBookBorrowHeader.totalPage)
            .setStartPage(this.ViewBag.listBookBorrowHeader.currentPage)
            .setPageClickHandler(self.onPageClick.bind(this))
        ;
        grid.setName('grid')
            .addColumns([
                { field: 'id', caption: 'Mã', size: '40%', sortable: true, resizable: true },
                { field: 'status', caption: 'Tình trạng', size: '50%', sortable: true, resizable: true },
                { field: 'returnDate',render:'date', caption: 'Ngày trả', size: '50%', sortable: true, resizable: true },

            ])
            //.addButton('btnInsert', 'Thêm', 'fa fa-plus', self.onbtnInsertClickGrid.bind(this))
            //.addButton('btnUpdate', 'Cập nhật', 'fa fa-pencil', self.onbtnUpdateClickGrid.bind(this))
            .addButton('btnDelete', 'Xóa', 'fa fa-trash-o', self.onbtnDeleteClickGrid.bind(this))
            .setIdColumn('id')
            .addRecords(self.ViewBag.listBookBorrowHeader.data).setPaginateOptions(pagi.end())
        ;
        if (this.parentId) {
            grid.createEvent('onDblClick', self.onDblClickGrid.bind(this));
        }

        content.addItem(grid.end());
    },
    onbtnInsertClickGrid: function () {
        this.openPopup({
            name: 'insertPopup',
            url: '/Admin/BookBorrowHeader/InsertBookBorrowHeader',
            title: 'Insert BookBorrowHeader',
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
            url: '/Admin/BookBorrowHeader/UpdateBookBorrowHeader/'+id,
            title: 'Update Role',
            width: '700px'
        });

    },
    onbtnDeleteClickGrid: function () {
        var self = this;
        w2confirm('Bạn có chắc chắn muốn xóa các dòng này không ?').yes(function () {
            var grid = self.findElement('grid');
            var id = grid.getSelection()[0];
            $.post('/api/BookBorrowHeader/Deletes', { id: id }, function (result) {
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
        debugger;
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
        $.post('/api/BookBorrowHeader/GetList',this.searchParam, function (result) {
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
    /*
     onDblClickGrid: function (e) {
     var self = this;
     var grid = this.findElement('grid');
     var record = grid.get(e.recid);
     console.log(record);
     var mess = {
     type: 'popupListBookBorrowHeader',
     data: record,
     callback: function () {
     self.close();
     }
     }
     this.sendMessage(mess);
     }
     */
    onDblClickGrid: function (e) {
        var self = this;
        var grid = this.findElement('grid');
        var record = grid.get(e.recid);
        this.sendMessage(record);
    }
});
