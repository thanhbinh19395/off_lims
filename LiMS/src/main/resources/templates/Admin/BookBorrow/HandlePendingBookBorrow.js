/**
 * Created by Thanh Binh on 3/9/2017.
 */
framework.factory('HandlePendingBookBorrow', {
    onPopupHandler: function (data) {
        debugger;
        if (data.eventType == 'remove') {
            var form = this.findElement('form');
            form.clear();
        }
        else if (data.eventType == '') {

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

            console.log(message);
        }
        else if (sender.pageName == 'insertBook') {
            data.data.BookCode = data.message.Code;
            data.data.BookName = data.message.Name;
            this.insertBBDetailHandler(sender, message.data);
        }
        else if (sender.pageName == 'ListUser') {
            var form = this.findElement('form');
            $.extend(form.record, message.data);
            form.refresh();
            sender.close && sender.close();
        }
    },
    onInitHeader: function (header) {
        header.setWidth('700px').setTitle('Handle Pending Book Borrow').setIcon('fa fa-list');

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
                    caption: 'Mã phiếu mượn',
                    type: 'popupListBookBorrow',
                    span: 1,
                    options: {caller: self}
                },
                {type: 'empty'},
                {field: 'name', caption: 'Borrowed By', type: 'text'},
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
                type: 'button', id: 'back', caption: 'Trở lại danh sách', icon: 'fa-list',
                onClick: self.onBtnBackClick.bind(this)
            })
            .addItem({
                type: 'button', id: 'save', caption: 'Lưu', icon: 'glyphicon glyphicon-floppy-saved',
                onClick: self.onBtnSaveClick.bind(this)
            })
        ;
        var grid = widget.setting.grid();

        grid.setName('grid')
            .setHeight('600px')
            .setIdColumn('id')
            .addColumns([
                {field: 'id', caption: 'Mã sản phẩm', size: '10%', resizable: true, sortable: true},
                {field: 'name', caption: 'Book Name', size: '30%', sortable: true, resizable: true},
                {field: 'publish_year', caption: 'Năm Xuất Bản', size: '10%', sortable: true, resizable: true},
                {field: 'author', caption: 'Tác giả', size: '10%', sortable: true, resizable: true},
                //{ field: 'image', caption: 'Hình', size: '15%', sortable: true, resizable: true },
                {field: 'bookCode', caption: 'Book Code', size: '15%', sortable: true, resizable: true},
                {
                    field: 'bookCategory.category_name',
                    caption: 'Thể loại',
                    size: '15%',
                    sortable: true,
                    resizable: true
                },
                {field: 'bookStatus.description', caption: 'Trạng Thái', size: '15%', sortable: true, resizable: true}
            ])
            .addButton('delete', 'Xóa', 'fa fa-times', self.onBtnDeleteClick.bind(self))
            .addButton('product', 'Chọn', 'fa fa-check', self.onChooseBookClick.bind(self))
            .addButton('insertBook', 'Thêm mới', 'fa fa-plus', self.onInsertBookClick.bind(self))
            .createEvent('onChange', self.onEditFieldGrid.bind(self)).createEvent('onSearch', self.onSearchBookGrid.bind(self))
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
    onSearchBookGrid: function (e) {
        console.log(e);
    },
    onBtnSaveClick: function () {
        var self = this;
        var form = this.findElement('form');

        if (form.record.bookBorrowId) {
            $.post('/api/BookBorrow/HandlePendingBookBorrow', {bookBorrowHeaderId: form.record.bookBorrowId}, function (r) {
                framework.common.cmdResultNoti(r);

            });
        }
    },
    onChangeForm: function (e) {
        if (e.target == 'CustomerPaid') {
            var self = this;
            e.done(function () {
                self.updateCharge(e.value_new);
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
        var grid = this.findElement('grid');
        var total = 0;
        $.each(grid.records, function (k, v) {
            total += v.BookPrice * v.Quantity;
        });
        return total;
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
            url: '/  /BookHandler/InsertBook',
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