/**
 * Created by Thanh Binh on 2/8/2017.
 */
framework.factory('ListUser', {
    onMessageReceive: function (sender, message) {
        if(sender.pageName =='InsertUser' || sender.pageName =='UpdateUser'){
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
                { field: 'name', caption: 'Name', type: 'text'},
                { field: 'phone', caption: 'Phone', type: 'text'},
                { field: 'address', caption: 'Address' , type: 'text'},
                { field: 'idcard', caption: 'ID Number', type: 'text' },
                { field: 'birthday', caption: 'Birthday', type: 'date' },
                { field: 'username', caption: 'Username' , type: 'text'},
                { field: 'password', caption: 'Password', type: 'text' },
                { field: 'roleId', caption: 'Role', type: 'popupListRole', options:{caller:self} },
            ])
        ;
        header.setTitle('Danh sách User')
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
        console.log(this.ViewBag);
        var pagi = widget.setting.pagination();
        pagi.setName('page')
            .setTotalPages(this.ViewBag.listUser.totalPage)
            .setStartPage(this.ViewBag.listUser.currentPage)
            .setPageClickHandler(self.onPageClick.bind(this))
        ;

        var grid = widget.setting.grid();
        grid.setName('grid')
            .addColumns([
                { field: 'id', caption: 'Mã vai trò', size: '40%', sortable: true, resizable: true },
                { field: 'name', caption: 'Name', size: '50%', sortable: true, resizable: true },
                { field: 'phone', caption: 'Phone', size: '50%', sortable: true, resizable: true },
                { field: 'email', caption: 'Email', size: '50%', sortable: true, resizable: true },
                { field: 'address', caption: 'Address', size: '50%', sortable: true, resizable: true },
                { field: 'idcard', caption: 'ID Number', size: '50%', sortable: true, resizable: true },
                { field: 'birthday', caption: 'Birthday', size: '50%', sortable: true, resizable: true, render:'date' },
                { field: 'username', caption: 'Username', size: '50%', sortable: true, resizable: true },
                { field: 'password', caption: 'Password', size: '50%', sortable: true, resizable: true },
                { field: 'role.name', caption: 'Role', size: '50%', sortable: true, resizable: true },
            ])
            .addButton('btnInsert', 'Thêm', 'fa fa-plus', self.onbtnInsertClickGrid.bind(this))
            .addButton('btnUpdate', 'Cập nhật', 'fa fa-pencil', self.onbtnUpdateClickGrid.bind(this))
            .addButton('btnDelete', 'Xóa', 'fa fa-trash-o', self.onbtnDeleteClickGrid.bind(this))
            .setIdColumn('id')
            .addRecords(self.ViewBag.listUser.data).setPaginateOptions(pagi.end())
        ;

        if (this.parentId) {
            grid.createEvent('onDblClick', self.onDblClickGrid.bind(this));
        }

        content.addItem(grid.end());
    },
    onbtnInsertClickGrid: function () {
        this.openPopup({
            name: 'insertPopup',
            url: '/Admin/User/InsertUser',
            title: 'Insert User',
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
            url: '/Admin/User/UpdateUser/'+id,
            title: 'Update User',
            width: '700px'
        });

    },
    onbtnDeleteClickGrid: function () {
        var self = this;
        w2confirm('Bạn có chắc chắn muốn xóa các dòng này không ?').yes(function () {
            var grid = self.findElement('grid');
            var id = grid.getSelection()[0];
            $.post('/api/User/Deletes', { id: id }, function (result) {
                if(result.success)
                    alertSuccess(result.message);
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
        $.post('/api/User/PageableSearch',this.searchParam, function (result) {
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
            type: 'popupListUser',
            data: record,
            callback: function () {
                self.close();
            }
        }
        this.sendMessage(mess);
    }
});
