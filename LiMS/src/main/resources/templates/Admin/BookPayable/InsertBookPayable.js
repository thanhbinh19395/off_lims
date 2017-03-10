/**
 * Created by Thanh Binh on 2/27/2017.
 */
framework.factory('InsertBookPayable', {
    onPopupHandler: function (data) {
        if (data.eventType == 'remove') {
            var form = this.findElement('form');
            form.clear();
        }
        else if (data.eventType == 'open') {
            if(!data.param)
                data.param = {};
            $.extend(data.param, {status: 1});
        }
    },
    onMessageReceive: function (sender, message) {
        if (sender.pageName == 'ListBookBorrow') {
            var form = this.findElement('form');
            var grid = this.findElement('grid');

            //form handler
            var formData = message.user;
            formData.name = '[' + message.user.username + ']' + message.user.name;
            formData.returnDate = message.returnDate;
            var actualReturnDate = new Date();
            actualReturnDate = actualReturnDate.setDate(actualReturnDate.getDate());
            formData.actualReturnDate = actualReturnDate;
            console.log(formData);
            $.extend(form.record, formData);
            form.refresh();

            //grid handler
            grid.clear();
            grid.add($.map(message.bookBorrowDetails, function (v) {
                return v.book;
            }));
        }
    },
    onInitHeader: function (header) {
        header.setWidth('700px').setTitle('Create BookPayable header').setIcon('fa fa-list');

    },
    onInitContent: function (content) {
        var self = this;

        var form = widget.setting.form();
        var returnDate = new Date();
        form.setName('form')
            .setFieldPerRow(2)
            .addFields([
                {
                    field: 'bookBorrowId',
                    caption: 'BB Header Id',
                    type: 'popupListBookBorrow',
                    span: 1,
                    options: {caller: self},
                    required: true
                },
                {type: 'empty'},
                {field: 'name', caption: 'User Name', type: 'text'},
                {field: 'phone', caption: 'Phone', type: 'text'},
                {field: 'address', caption: 'Address', type: 'text'},
                {field: 'email', caption: 'Email', type: 'text'},
                {field: 'idcard', caption: 'ID Number', type: 'text'},
                {field: 'birthday', caption: 'Birthday', type: 'date'},
                {field: 'returnDate', caption: 'Returned Date', type: 'date', span: 2, required: true},
                {field: 'actualReturnDate', caption: 'Actual Returned Date', type: 'date', span: 2, required: true},
            ])
        ;
        var toolbar = widget.setting.toolbar();
        toolbar.setName('toolbar')
            .addItem({
                type: 'button', id: 'back', caption: 'Back', icon: 'fa-list',
                onClick: self.onBtnBackClick.bind(this)
            })
            .addItem({
                type: 'button', id: 'save', caption: 'Save', icon: 'glyphicon glyphicon-floppy-saved',
                onClick: self.onBtnSaveClick.bind(this)
            })
        ;
        var grid = widget.setting.grid();

        grid.setName('grid')
            .setHeight('600px')
            .setIdColumn('id')
            .addColumns([
                {field: 'id', caption: 'Id', size: '10%', resizable: true, sortable: true},
                {field: 'name', caption: 'Book Name', size: '30%', sortable: true, resizable: true},
                {field: 'publish_year', caption: 'Publishing Year', size: '10%', sortable: true, resizable: true},
                {field: 'author', caption: 'Author', size: '10%', sortable: true, resizable: true},
                //{ field: 'image', caption: 'Hình', size: '15%', sortable: true, resizable: true },
                {field: 'bookCode', caption: 'Book Code', size: '15%', sortable: true, resizable: true},
                {
                    field: 'bookCategory.category_name',
                    caption: 'Category',
                    size: '15%',
                    sortable: true,
                    resizable: true
                },
                {field: 'bookStatus.description', caption: 'Status', size: '15%', sortable: true, resizable: true}
            ])
        ;


        content.setWidth('700px').addItem(form.end()).addItem(toolbar.end()).addItem(grid.end());
    },
    onBtnBackClick: function () {
        if (this.parentId) {
            this.close();
        }
        else {
            window.location.replace("/Admin/BookPayable/ListBookPayable");
        }
    },
    onSearchBookGrid: function (e) {
        console.log(e);
    },
    onBtnSaveInventoryClick: function () {
        var curBP = this.getCurrentBP();
        var self = this;
        if (curBP) {
            $.post('/api/BookBorrowHeader/Save', {
                header: this.form.record,
                detail: this.w2ui.grid.record
            }, function (data) {
                framework.common.cmdResultNoti(data);
            });
        }
    },
    onBtnSaveFinishClick: function () {
        var curBP = this.getCurrentBP();
        var self = this;
        var form = this.findElement('form');
        var grid = this.findElement('grid');
        console.log({header: form.record, detail: grid.records});
        if (curBP) {

            $.post('/api/BookBorrowHeader/Save', {header: form.record, detail: grid.records}, function (data) {
                framework.common.cmdResultNoti(data);
            });
        }
    },
    onBtnSaveClick: function () {
        var self = this;
        var curBP = this.getCurrentBP();
        if (curBP) {
            $.ajax({
                url: "/api/BookPayable/Insert",
                type: "POST",
                data: JSON.stringify(curBP),
                success: function (r) {
                    framework.common.cmdResultNoti(r);
                    if (r.success) {
                        self.toInitState();
                        if (self.parentId)
                            self.sendMessage(r);
                    }
                },
                dataType: "json",
                contentType: "application/json"
            });
        }
    },
    insertBPDetailHandler: function (sender, data) {
        var grid = this.findElement('grid');
        var existBPDetail = grid.get(data.id);
        if (existBPDetail) {
            alert("");
        }
        else {
            grid.add(data);
        }
        this.updateTotal();
    },

    getCurrentBP: function () {
        var form = this.findElement('form');
        var grid = this.findElement('grid');
        if (form.validate() != 0)
            return;


        var header = {
            returnDate: form.record.returnDate,
            actualReturnDate: form.record.actualReturnDate,
            bookBorrowId: form.record.bookBorrowId
        };
        var details = $.map(grid.records, function (v) {
            return {
                bookId: v.id,
                note: "hello"
            }
        });
        return {
            header: header,
            details: details
        }
    },
    toInitState: function () {
        var form = this.findElement('form');
        var grid = this.findElement('grid');
        grid.clear();
        form.clear();
    },
});