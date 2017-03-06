/**
 * Created by dylan on 2/23/2017.
 */
/**
 * Created by dylan on 2/23/2017.
 */
framework.factory('CreateBB', {
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
    },
    onMessageReceive: function (sender, message) {
        if (sender.pageName == 'ListBook' ) {
            this.insertBBDetailHandler(sender, message);
        }
        else if (sender.pageName == 'InsertBook'){
            message.data.recid = message.data.id;
            this.insertBBDetailHandler(sender, message.data);
            if(message.success)
                sender.close();
        }
        else if (sender.pageName == 'ListUser') {
            var form = this.findElement('form');
            $.extend(form.record,message.data);
            form.refresh();
            sender.close && sender.close();
        }
    },
    onInitHeader: function (header) {
        header.setWidth('700px').setTitle('Tạo mới phiếu mượn sách').setIcon('fa fa-list');

    },
    onInitContent: function (content) {
        var self = this;

        var form = widget.setting.form();
        var returnDate = new Date();
        returnDate = returnDate.setDate(returnDate.getDate() + 3);
        form.setName('form')
            .setFieldPerRow(2)
            .addFields([
                { field: 'createdUsername', caption: 'Người lập', type: 'text', html:{attr:{disabled:'disabled'}}, span : 1},
                { type: 'empty'},
                { field: 'userId', caption: 'Người mượn', type: 'popupListUser',options:{caller:self}, span :1, required : true },
                { type: 'empty'},
                { field: 'name', caption: 'Name', type: 'text'},
                { field: 'phone', caption: 'Phone', type: 'text'},
                { field: 'address', caption: 'Address' , type: 'text'},
                { field: 'email', caption: 'Email' , type: 'text'},
                { field: 'idcard', caption: 'ID Number', type: 'text' },
                { field: 'birthday', caption: 'Birthday', type: 'date' },
                { field: 'returnDate', caption: 'Hạn trả', type: 'date', span : 2, required : true},
            ])
            .setRecord({
                returnDate : returnDate,
            })
        ;
        var toolbar = widget.setting.toolbar();
        toolbar.setName('toolbar')
            .addItem({
                type: 'button', id: 'back', caption: 'Trở lại danh sách', icon: 'fa-list',
                onClick: self.onBtnBackClick.bind(this)
            })
            .addItem({
                type: 'button', id: 'save', caption: 'Lưu', icon: 'glyphicon glyphicon-floppy-saved',
                onClick: self.onBtnSaveClick.bind(this)
            })
            .addItem({ type: 'spacer' })
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
                                    }, {   name: val,}  );
                                }
                            });
                            $(kpe.target).val(null);
                        }
                    });
                });
            });
        ;
        var grid = widget.setting.grid();

        grid.setName('grid').setProperty({ show: {} })
            .setHeight('600px')
            .setIdColumn('id')
            .addColumns([
                //{ field: 'id', caption: 'Mã Sách', size: '10%', resizable: true, sortable: true },
                { field: 'bookCode', caption: 'Book Code', size: '15%', sortable: true, resizable: true, render:function(record){
                    record.bookId = record.id;
                    return record.bookCode;
                } },
                { field: 'name', caption: 'Tên Sách', size: '30%', sortable: true, resizable: true },
                { field: 'publish_year', caption: 'Năm Xuất Bản', size: '10%', sortable: true, resizable: true },
                { field: 'author', caption: 'Tác giả', size: '10%', sortable: true, resizable: true },
                { field: 'bookCategory.category_name', caption: 'Thể loại', size: '15%', sortable: true, resizable: true },
                { field: 'bookStatus.description', caption: 'Trạng Thái', size: '15%', sortable: true, resizable: true }
            ])
            .addButton('delete', 'Xóa', 'fa fa-times', self.onBtnDeleteClick.bind(self))
            .addButton('product', 'Chọn', 'fa fa-check', self.onChooseBookClick.bind(self))
            .addButton('insertBook', 'Thêm mới', 'fa fa-plus', self.onInsertBookClick.bind(self))
            .createEvent('onChange', self.onEditFieldGrid.bind(self)).createEvent('onSearch', self.onSearchBookGrid.bind(self))
        ;



        content.setWidth('700px').addItem(form.end()).addItem(toolbar.end()).addItem(grid.end());
    },
    onBtnBackClick: function () {
        window.location.replace("/Admin/BookBorrowHeader/ListBookBorrowHeader");
    },
    onSearchBookGrid: function (e) {
        console.log(e);
    },
    onBtnSaveClick: function () {
        var curBB = this.getCurrentBB();
        if (curBB) {
            $.ajax({
                    url:"/api/BookBorrow/Insert",
                    type: "POST",
                    data: JSON.stringify( curBB ),
                    success: function(r){
                        framework.common.cmdResultNoti(r);
                    },
                    dataType: "json",
                    contentType: "application/json"
                });
        }
    },

    onBtnDeleteClick: function (e) {
        var grid = this.findElement('grid');
        grid.delete(true);
        this.updateTotal();
    },
    onEditFieldGrid: function (e) {
        var self = this;
        e.done(function () {
            self.refreshGrid(e.recid);
        });
    },
    refreshGrid: function (recid) {
        var grid = this.findElement('grid');
        grid.mergeChanges();
        if (recid)
            grid.refresh(recid);
        else
            grid.refresh();
        this.updateTotal();
    },
    updateTotal: function () {
        var form = this.findElement('form');
        //form.record.Total = this.calculateTotal();
        form.refresh();
    },
    updateCharge: function (paid) {
        var form = this.findElement('form');
        form.record.Charge = paid - form.record.Total;
        form.refresh();
    },
    calculateTotal: function () {

        return 1;
    },
    insertBBDetailHandler: function (sender, data) {
        var grid = this.findElement('grid');
        var existBBDetail = grid.get(data.id);
        if (existBBDetail) {
            alert("ko dc trung sach");
        }
        else {
            grid.add(data);
        }
        this.updateTotal();
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
            returnDate : form.record.returnDate,
            userId : form.record.userId
        };
        var details = $.map(grid.records,function(v){
            return {
                bookId:v.bookId,
                note:"hello"
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