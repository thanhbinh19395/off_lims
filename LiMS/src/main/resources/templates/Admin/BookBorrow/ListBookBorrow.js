/**
 * Created by dylan on 2/21/2017.
 */
framework.factory('ListBookBorrow', {
    onMessageReceive: function (sender, data) {
        if(sender.pageName =='InsertBookBorrow'){
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
                { field: 'username', type: 'text', required: false, caption: "Người mượn", render: function(r){
                    return '['+r.user.username +'] ' + r.user.name;
                } },
                { field: 'returnDate', type: 'date', required: false, caption: "Ngày trả" },
                { field: 'status', type: 'text', required: false, caption: "Trạng thái" },
            ])
        ;
        header.setTitle('Danh sách Phiếu mượn')
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
            .setTotalPages(this.ViewBag.listBookBorrow.totalPage)
            .setStartPage(this.ViewBag.listBookBorrow.currentPage)
            .setPageClickHandler(self.onPageClick.bind(this))
        ;
        grid.setName('grid')
            .addColumns([
                { field: 'id', caption: 'Mã', size: '40%', sortable: true, resizable: true },
                { field: 'username', size: '50%', sortable: true, resizable: true, caption: "Người mượn", render: function(r){
                    return '['+r.user.username +']' + r.user.name;
                } },
                { field: 'created_date',render:'date', caption: 'Ngày lập', size: '50%', sortable: true, resizable: true },
                { field: 'returnDate',render:'date', caption: 'Hạn trả', size: '50%', sortable: true, resizable: true },
                { field: 'createdUser',size: '40%', sortable: true, resizable: true, caption: "Người lập", render: function(r){
                    return '['+r.created_by.username +']' + r.created_by.name;
                } },
                { field: 'status', size: '40%', sortable: true, resizable: true, caption: "Trạng thái",render:function (r) {
                    switch (r.status){
                        case 0:
                            return 'Status ne';
                            break;
                        case 1:
                            return 'Status ne';
                            break;
                        case 2:
                            return 'Status ne';
                            break;
                        case 3:
                            return 'Status ne';
                            break;
                    }

                } },
                {
                    field: 'details', caption: 'Xem chi tiết', size: '30%', sortable: true, resizable: true, render: function (r) {
                    var a = $("<a>");
                    a.attr('href', '#');
                    a.attr('type', 'click');
                    a.html('Xem chi tiết');
                    return a[0].outerHTML;
                }
                }
            ])
            .addButton('btnInsert', 'Thêm', 'fa fa-plus', self.onbtnInsertClickGrid.bind(this))
            .addButton('btnUpdate', 'Xem chi tiết', 'fa fa-pencil', self.onbtnViewClickGrid.bind(this))
            //.addButton('btnDelete', 'Xóa', 'fa fa-trash-o', self.onbtnDeleteClickGrid.bind(this))
            .setIdColumn('id')
            .setRecords(self.ViewBag.listBookBorrow.data).setPaginateOptions(pagi.end())
            .createEvent('onClick', this.onGridClick.bind(this))
        ;
        if (this.parentId) {
            grid.createEvent('onDblClick', self.onDblClickGrid.bind(this));
        }

        content.addItem(grid.end());
    },
    onbtnInsertClickGrid: function () {
        this.openPopup({
            name: 'insertPopup',
            url: '/Admin/BookBorrow/InsertBookBorrow',
            title: 'Insert BookBorrow',
            width: '700px'
        });
    },
    onbtnViewClickGrid: function () {
        var grid = this.findElement('grid');
        var id = grid.getSelection()[0];
        if (!id) {
            //thong bao = noty
            alert("vui long chon");
            return;
        }
        this.openPopup({
            name: 'updatePopup',
            url: '/Admin/BookBorrow/ViewBookBorrow/'+id,
            title: 'View BookBorrow',
            width: '700px'
        });

    },
    onbtnDeleteClickGrid: function () {
        var self = this;
        w2confirm('Bạn có chắc chắn muốn xóa các dòng này không ?').yes(function () {
            var grid = self.findElement('grid');
            var id = grid.getSelection()[0];
            $.post('/api/BookBorrow/Deletes', { id: id }, function (result) {
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
        $.post('/api/BookBorrow/GetList',this.searchParam, function (result) {
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
     type: 'popupListBookBorrow',
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
    },
    onGridClick: function (e) {
        if (e.column == 6) {
            var self = this;
            e.onComplete = function (data) {
                if ($(data.originalEvent.srcElement).attr('type') == 'click') {
                    self.openPopup({
                        name: 'ViewPopup',
                        url: '/Admin/BookBorrow/ViewBookBorrow/'+e.recid,
                        title: 'View BookBorrow',
                        width: '700px'
                    });
                }

            }
        }
    }
});
