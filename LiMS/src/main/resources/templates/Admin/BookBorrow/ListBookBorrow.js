/**
 * Created by dylan on 2/21/2017.
 */
framework.factory('ListBookBorrow', {
    onMessageReceive: function (sender, data) {
        if (sender.pageName == 'InsertBookBorrow') {
            if (data.success) {
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
                {
                    field: 'userId',
                    type: 'popupListUser',
                    required: false,
                    caption: "User Id",
                    options: {caller: self}
                },
                //{field: 'returnDate', type: 'date', required: false, caption: "Ngày trả"},
                {
                    field: 'statusName', type: 'list', required: true, caption: 'Status', options: {
                    items: [
                        {id: -2, text: 'ALL'},
                        {id: 0, text: 'PENDING'},
                        {id: 1, text: 'INPROGRESS'},
                        {id: 2, text: 'SOLVED'},
                        {id: -1, text: 'CANCELLED'},
                    ], selected: {id: -2},
                }
                }
            ])
        ;
        header.setTitle('BB Header List')
            .setIcon('fa fa-list');

        var formFooter = widget.setting.toolbar();
        formFooter.addItem({
            type: 'button', id: 'btn-search', caption: 'Search', icon: 'fa-search',
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
                type: 'button', id: 'btn-reload', caption: 'Reload', icon: 'fa-refresh',
                onClick: self.onbtnReloadClick.bind(self)
            })
            .addRight({
                type: 'button', id: 'btn-search', caption: 'Search', icon: 'fa-search',
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
                {field: 'id', caption: 'Id', size: '40%', sortable: true, resizable: true},
                {
                    field: 'username',
                    size: '50%',
                    sortable: true,
                    resizable: true,
                    caption: "Borrowed By",
                    render: function (r) {
                        return '[' + r.user.username + ']' + r.user.name;
                    }
                },
                {
                    field: 'created_date',
                    render: 'date',
                    caption: 'Created Date',
                    size: '50%',
                    sortable: true,
                    resizable: true
                },
                {field: 'returnDate', render: 'date', caption: 'Returned Date', size: '50%', sortable: true, resizable: true},
                {
                    field: 'createdUser',
                    size: '40%',
                    sortable: true,
                    resizable: true,
                    caption: "Created By",
                    render: function (r) {
                        return '[' + r.created_by.username + ']' + r.created_by.name;
                    }
                },
                {
                    field: 'status',
                    size: '40%',
                    sortable: true,
                    resizable: true,
                    caption: "Status",
                    render: function (r) {
                        switch (r.status) {
                            case 0:
                                return 'PENDING';
                                break;
                            case 1:
                                return 'INPROGRESS';
                                break;
                            case 2:
                                return 'SOLVED';
                                break;
                            case -1:
                                return 'CANCELLED';
                                break;
                        }

                    }
                },
                {
                    field: 'details',
                    caption: 'View Details',
                    size: '30%',
                    sortable: true,
                    resizable: true,
                    render: function (r) {
                        var a = $("<a>");
                        a.attr('href', '#');
                        a.attr('type', 'click');
                        a.html('View Details');
                        return a[0].outerHTML;
                    }
                }
            ])
            .addButton('btnInsert', 'Add', 'fa fa-plus', self.onbtnInsertClickGrid.bind(this))
            .addButton('btnUpdate', 'View Details', 'fa fa-pencil', self.onbtnViewClickGrid.bind(this))
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
            alert("Please choose a row");
            return;
        }
        this.openPopup({
            name: 'updatePopup',
            url: '/Admin/BookBorrow/ViewBookBorrow/' + id,
            title: 'View BookBorrow',
            width: '700px'
        });

    },
    onbtnDeleteClickGrid: function () {
        var self = this;
        w2confirm('Do you want to delete this record ?').yes(function () {
            var grid = self.findElement('grid');
            var id = grid.getSelection()[0];
            $.post('/api/BookBorrow/Deletes', {id: id}, function (result) {
                if (result.success) {
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
        if(form.record.statusName.id != -2 )
            this.searchParam.status = form.record.statusName.id;
        else
            this.searchParam.status = null;
        this.reloadGridData();
        this.findElement("headerContent").toggle();
    },
    onPageClick: function (event, page) {
        var grid = this.findElement('grid');
        this.searchParam = {
            page: page,
            size: 1
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
    reloadGridData: function () {
        var grid = this.findElement('grid');
        $.post('/api/BookBorrow/GetList', this.searchParam, function (result) {
            if (result.success) {
                grid.clear();
                grid.add(result.data);
                if (grid.pagination)
                    grid.pagination.reset(result.currentPage, result.totalPage);
            }
            else {

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
                        url: '/Admin/BookBorrow/ViewBookBorrow/' + e.recid,
                        title: 'View BookBorrow',
                        width: '700px'
                    });
                }

            }
        }
    }
});
