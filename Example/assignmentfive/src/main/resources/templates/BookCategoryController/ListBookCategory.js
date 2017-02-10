/**
 * Created by WIN8.1 on 10/02/2017.
 */
/**
 * Created by Thanh Binh on 2/8/2017.
 */
framework.factory('ListBookCategory', {
    onMessageReceive: function (sender, message) {
        if(sender.pageName =='InsertBookCategory' || sender.pageName =='UpdateBookCategory'){
            this.onbtnReloadClick();

            //close page con
            sender.close();
        }
    },
    onInitHeader: function (header) {
        header.setName('header');
        var self = this;
        var form = widget.setting.form();
        form.setName('searchForm')
            .setFieldPerRow(1) // so cot trong form
            .addFields([
                { field: 'category_name', type: 'text', required: false, caption: "Tên loại" },
            ])
        ;
        header.setTitle('Danh sách loại sách')
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
        grid.setName('grid')
            .addColumns([
                { field: 'id', caption: 'Mã loại sách', size: '40%', sortable: true, resizable: true },
                { field: 'category_name', caption: 'Tên loại sách', size: '50%', sortable: true, resizable: true },
            ])
            .addButton('btnInsert', 'Thêm', 'fa fa-plus', self.onbtnInsertClickGrid.bind(this))
            .addButton('btnUpdate', 'Cập nhật', 'fa fa-pencil', self.onbtnUpdateClickGrid.bind(this))
            .addButton('btnDelete', 'Xóa', 'fa fa-trash-o', self.onbtnDeleteClickGrid.bind(this))
            .setIdColumn('id')
            .addRecords(self.ViewBag.listBookCategory)
        ;

        if (this.parentId) {
            grid.createEvent('onDblClick', self.onDblClickGrid.bind(this));
        }

        content.addItem(grid.end());
    },
    onbtnInsertClickGrid: function () {
        this.openPopup({
            name: 'insertPopup',
            url: '/BookCategory/InsertBookCategory',
            title: 'Insert Book Category',
            width: '700px'
        });
    },
    onbtnUpdateClickGrid: function () {
        var grid = this.findElement('grid');
        var id = grid.getSelection()[0];
        if (!id) {
            //thong bao = noty
            return;
        }
        this.openPopup({
            name: 'updatePopup',
            url: '/BookCategory/UpdateBookCategory/'+id,
            title: 'Update Book Category',
            width: '700px'
        });

    },
    onbtnDeleteClickGrid: function () {
        var self = this;
        w2confirm('Bạn có chắc chắn muốn xóa các dòng này không ?').yes(function () {
            var grid = self.findElement('grid');
            var id = grid.getSelection()[0];
            $.post('/api/BookCategory/Deletes', { id: id }, function (data) {
                alertSuccess('Deleted !');
                self.onbtnReloadClick();
            });
        });
    },
    onbtnSearchClickSearchForm: function (evt) {

    },
    onPageClick: function (event, page) {

    },
    onbtnReloadClick: function (evt) {
        var grid = this.findElement('grid');
        var form = this.findElement('searchForm');

        //reload grid data
        $.post('/api/BookCategory/GetList',null, function (data) {
            grid.clear();
            grid.add(data);
        });

        //clear form search + param
        this.searchParam = {};
        form.clear();
    },
    onDblClickGrid: function (e) {
        var self = this;
        var grid = this.findElement('grid');
        var record = grid.get(e.recid);
        var mess = {
            type: 'popupListBookCategory',
            data: record,
            callback: function () {
                self.close();
            }
        }
        this.sendMessage(mess);
    }
});
