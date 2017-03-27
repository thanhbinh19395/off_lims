/**
 * Created by dylan on 2/23/2017.
 */
/**
 * Created by dylan on 2/23/2017.
 */
framework.factory('InsertBookBorrow', {
    onPopupHandler: function (data) {
        if (data.eventType == 'remove') {
            var form = this.findElement('form');
            form.record.name = null;
            form.record.phone = null;
            form.record.address = null;
            form.record.email = null;
            form.record.idcard = null;
            form.record.birthday = null;
            form.refresh();
        }
        else if(data.eventType == 'received'){
            if(!data.message.borrowable)
                data.buttonRemove.click();
        }
    },
    onMessageReceive: function (sender, message) {
        if (sender.pageName == 'ListBook') {
            this.insertBBDetailHandler(sender, message);
        }
        else if (sender.pageName == 'InsertBook') {
            message.data.recid = message.data.id;
            this.insertBBDetailHandler(sender, message.data);
            if (message.success)
                sender.close();
        }
        else if (sender.pageName == 'ListUser') {
            var form = this.findElement('form');
            if(message.borrowable == false){
                alert("[" +message.username +"]" + message.name + " is not borrowable");
                form.clear();
                return 3;
            }
            $.extend(form.record, message);
            form.refresh();
            debugger
            sender.close && sender.close();
        }
    },
    onInitHeader: function (header) {
        header.setWidth('700px').setTitle('Create new Book Bookborrow Header').setIcon('fa fa-list');

    },
    onInitContent: function (content) {
        var self = this;

        var form = widget.setting.form();
        var returnDate = new Date();
        returnDate = returnDate.setDate(returnDate.getDate() + 3);
        form.setName('form')
            .setFieldPerRow(2)
            .addFields([
                { field: 'userId', caption: 'Borrowed By', type: 'popupListUser',options:{caller:self}, span :1, required : true },
                {type: 'empty'},
                {field: 'name', caption: 'Name', type: 'text'},
                {field: 'phone', caption: 'Phone', type: 'text'},
                {field: 'address', caption: 'Address', type: 'text'},
                {field: 'email', caption: 'Email', type: 'text'},
                {field: 'idcard', caption: 'ID Number', type: 'text'},
                {field: 'birthday', caption: 'Birthday', type: 'date'},
                { field: 'returnDate', caption: 'Returned Date', type: 'date', span : 2, required : true},
            ])
            .setRecord({
                returnDate: returnDate,
            })
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
            .addItem({type: 'spacer'})
            .addItem({
                type: 'html', id: 'item5',
                html: function (item) {
                    var html =
                        '<div style="padding: 3px 10px;">' +
                        ' Book:' +
                        '    <input id="book" size="20" placeholder="Name, Code" ' +
                        '         style="padding: 3px; border-radius: 2px; border: 1px solid silver" />' +
                        '</div>';
                    return html;
                }
            })
            .createEvent('onRender', function (event) {
                event.done(function (e) {
                    var inputBook = $(e.box).find('#book');
                    inputBook.dblclick(function (dce) {
                        self.onChooseBookClick();
                    });
                    inputBook.keypress(function (kpe) {
                        if (kpe.key == 'Enter') {
                            var val = $(kpe.target).val();
                            $.post('/api/Book/Search', {
                                name: val,
                            }, function (result) {
                                var data = result.data;
                                if (data.length == 0) {
                                    alert('Book Not found !');
                                }
                                else if (data.length == 1) {
                                    self.insertBBDetailHandler(null, data[0]);
                                }
                                else {
                                    self.openPopup({
                                        name: 'insertPopup',
                                        url: '/Admin/Book/ListBook',
                                        width: 600
                                    }, {name: val,});
                                }
                            });
                            $(kpe.target).val(null);
                        }
                    });
                });
            });
        ;
        var grid = widget.setting.grid();

        grid.setName('grid').setProperty({show: {}})
            .setHeight('600px')
            .setIdColumn('id')
            .addColumns([
                //{ field: 'id', caption: 'Id Sách', size: '10%', resizable: true, sortable: true },
                { field: 'bookCode', caption: 'Book Code', size: '15%', sortable: true, resizable: true, render:function(record){
                    record.bookId = record.id;
                    return record.bookCode;
                } },
                { field: 'name', caption: 'Book Name', size: '30%', sortable: true, resizable: true },
                { field: 'publish_year', caption: 'Publishing Year', size: '10%', sortable: true, resizable: true },
                { field: 'author', caption: 'Author', size: '10%', sortable: true, resizable: true },
                { field: 'bookCategory.category_name', caption: 'Category', size: '15%', sortable: true, resizable: true },
                { field: 'bookStatus.description', caption: 'Status', size: '15%', sortable: true, resizable: true }
            ])
            .addButton('delete', 'Delete', 'fa fa-times', self.onBtnDeleteClick.bind(self))
            .addButton('product', 'Choose', 'fa fa-check', self.onChooseBookClick.bind(self))
            .addButton('insertBook', 'Add new', 'fa fa-plus', self.onInsertBookClick.bind(self))
        ;


        content.setWidth('700px').addItem(form.end()).addItem(toolbar.end()).addItem(grid.end());
    },
    onBtnBackClick: function () {
        if (this.parentId) {
            this.close();
        }
        else {
            window.location.replace("/Admin/BookBorrow/ListBookBorrow");
        }
    },
    onBtnSaveClick: function () {
        var self = this;
        var curBB = this.getCurrentBB();
        if (curBB) {
            $.ajax({
                url: "/api/BookBorrow/Insert",
                type: "POST",
                data: JSON.stringify(curBB),
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

    onBtnDeleteClick: function (e) {
        var grid = this.findElement('grid');
        grid.delete(true);
    },
    insertBBDetailHandler: function (sender, data) {
        var grid = this.findElement('grid');
        var existBBDetail = grid.get(data.id);
        if (existBBDetail) {
            alert("Do not choose Duplicated Book");
        }
        else {
            grid.add(data);
        }
    },

    onInsertBookClick: function () {
        this.openPopup({
            name: 'insertPopup',
            url: '/Admin/Book/InsertBook',
            width: 600
        });
    },
    onChooseBookClick: function () {
        this.openPopup({
            name: 'insertPopup',
            url: '/Admin/Book/ListBook',
            width: 600
        });
    },
    getCurrentBB: function () {
        var form = this.findElement('form');
        var grid = this.findElement('grid');
        if (form.validate() != 0)
            return;


        var header = {
            returnDate: form.record.returnDate,
            userId: form.record.userId
        };
        var details = $.map(grid.records, function (v) {
            return {
                bookId: v.bookId,
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
    onLoadComplete: function () {
        this.$contentEl.find('#product').focus();
    },
});