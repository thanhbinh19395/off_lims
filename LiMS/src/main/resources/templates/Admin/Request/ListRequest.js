/**
 * Created by dylan on 2/18/2017.
 */
framework.factory('ListRequest', {
    onMessageReceive: function (sender, data) {
        if (sender.pageName == 'InsertRequest' || sender.pageName == 'UpdateRequest') {
            if (data.success) {
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
                {field: 'book_name', type: 'text', required: false, caption: "Book Name"},
                {field: 'author', type: 'text', required: false, caption: "Author"},
                {field: 'status', type: 'text', required: false, caption: "Status"},
            ])
        ;
        header.setTitle('List Request')
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
        pagi.setName('page')
            .setTotalPages(this.ViewBag.listRequest.totalPage)
            .setStartPage(this.ViewBag.listRequest.currentPage)
            .setPageClickHandler(self.onPageClick.bind(this))
        ;
        grid.setName('grid')
            .addColumns([
                {field: 'id', caption: 'Id', size: '10%', sortable: true, resizable: true},
                {field: 'book_name', caption: 'Book Name', size: '45%', sortable: true, resizable: true},
                {field: 'author', caption: 'Author ', size: '45%', sortable: true, resizable: true},
                {
                    field: 'created_by.username',
                    caption: 'Username',
                    size: '45%',
                    sortable: true,
                    resizable: true
                },
                {field: 'status', caption: 'Status', size: '45%', sortable: true, resizable: true},
            ])
            .addButton('btnApprove', 'Approve', 'fa fa-plus', self.onbtnInsertClickGrid.bind(this))
            .addButton('btnReject', 'Reject', 'fa fa-pencil', self.onbtnUpdateClickGrid.bind(this))
            .setIdColumn('id')
            .setRecords(self.ViewBag.listRequest.data).setPaginateOptions(pagi.end())
        ;
        if (this.parentId) {
            grid.createEvent('onDblClick', self.onDblClickGrid.bind(this));
        }

        content.addItem(grid.end());
    },
    onbtnInsertClickGrid: function () {
        var grid = this.findElement('grid');
        var id = grid.getSelection()[0];
        if (!id) {
            //thong bao = noty
            return;
        }
        this.openPopup({
            name: 'updatePopup',
            url: '/Admin/Request/Approve/'+id,
            title: 'Update Role',
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
            url: '/Admin/Request/Reject/'+id,
            title: 'Update Role',
            width: '700px'
        });

    },
    onbtnDeleteClickGrid: function () {
        var self = this;
        w2confirm('Do you want to delete this record ??').yes(function () {
            var grid = self.findElement('grid');
            var id = grid.getSelection()[0];
            $.post('/api/Request/Deletes', {id: id}, function (result) {
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
        $.post('/api/Request/GetList',this.searchParam, function (result) {
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
     type: 'popupListRequest',
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
