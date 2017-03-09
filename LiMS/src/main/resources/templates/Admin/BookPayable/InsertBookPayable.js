/**
 * Created by Thanh Binh on 2/27/2017.
 */
framework.factory('InsertBookPayable', {
    onPopupHandler: function (data) {
        debugger;
        if (data.eventType == 'remove') {
            var form = this.findElement('form');
            form.clear();
        }
        else if(data.eventType == ''){

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
            grid.add($.map(message.bookBorrowDetails,function(v){
                return v.book;
            }));

            console.log(message);
        }
        else if (sender.pageName == 'insertBook') {
            data.data.BookCode = data.message.Code;
            data.data.BookName = data.message.Name;
            this.insertBPDetailHandler(sender, message.data);
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
                { field: 'bookBorrowId', caption: 'Id phiếu lập', type: 'popupListBookBorrow', span : 1, options:{caller:self}},
                { type: 'empty'},
                { field: 'name', caption: 'Tên người mượn', type: 'text'},
                { field: 'phone', caption: 'Phone', type: 'text'},
                { field: 'address', caption: 'Address' , type: 'text'},
                { field: 'email', caption: 'Email' , type: 'text'},
                { field: 'idcard', caption: 'ID Number', type: 'text' },
                { field: 'birthday', caption: 'Birthday', type: 'date' },
                { field: 'returnDate', caption: 'Returned Date', type: 'date', span : 2, required : true},
                { field: 'actualReturnDate', caption: 'Return Date', type: 'date', span : 2, required : true},
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
                { field: 'id', caption: 'Id', size: '10%', resizable: true, sortable: true },
                { field: 'name', caption: 'Book Name', size: '30%', sortable: true, resizable: true },
                { field: 'publish_year', caption: 'Publishing Year', size: '10%', sortable: true, resizable: true },
                { field: 'author', caption: 'Author', size: '10%', sortable: true, resizable: true },
                //{ field: 'image', caption: 'Image', size: '15%', sortable: true, resizable: true },
                { field: 'bookCode', caption: 'Book Code', size: '15%', sortable: true, resizable: true },
                { field: 'bookCategory.category_name', caption: 'Category Name', size: '15%', sortable: true, resizable: true },
                { field: 'bookStatus.description', caption: 'Status', size: '15%', sortable: true, resizable: true }
            ])
            .addButton('delete', 'Delete', 'fa fa-times', self.onBtnDeleteClick.bind(self))
            .addButton('product', 'Choose', 'fa fa-check', self.onChooseBookClick.bind(self))
            .addButton('insertBook', 'Add new', 'fa fa-plus', self.onInsertBookClick.bind(self))
            .createEvent('onChange', self.onEditFieldGrid.bind(self)).createEvent('onSearch', self.onSearchBookGrid.bind(self))
        ;



        content.setWidth('700px').addItem(form.end()).addItem(toolbar.end()).addItem(grid.end());
    },
    onBtnBackClick: function () {
        window.location.replace("/Admin/BookBorrow/ListBookBorrow");
    },
    onSearchBookGrid: function (e) {
        console.log(e);
    },
    onBtnSaveInventoryClick: function () {
        var curBP = this.getCurrentBP();
        if (curBP) {
            $.post('/api/BookBorrowHeader/Save', {header: this.form.record, detail: this.w2ui.grid.record}, function (data) {
                framework.common.cmdResultNoti(data);
            });
        }
    },
    onBtnSaveFinishClick: function () {
        var curBP = this.getCurrentBP();
        var self = this;
        var form = this.findElement('form');
        var grid = this.findElement('grid');
        console.log({header: form.record, detail:  grid.records});
        if (curBP) {

            $.post('/api/BookBorrowHeader/Save', {header: form.record, detail:  grid.records}, function (data) {
                framework.common.cmdResultNoti(data);
            });
        }
    },
    onBtnSaveClick: function () {
        var self = this;
        var curBP = this.getCurrentBP();
        if (curBP) {
            $.ajax({
                url:"/api/BookPayable/Insert",
                type: "POST",
                data: JSON.stringify( curBP ),
                success: function(r){
                    framework.common.cmdResultNoti(r);
                    if(r.success){
                        self.toInitState();
                        if(self.parentId)
                            self.sendMessage(r);
                    }
                },
                dataType: "json",
                contentType: "application/json"
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
    insertBPDetailHandler: function (sender, data) {
        var grid = this.findElement('grid');
        var existBPDetail = grid.get(data.id);
        if (existBPDetail) {
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
    getCurrentBP: function () {
        var form = this.findElement('form');
        var grid = this.findElement('grid');
        if (form.validate() != 0)
            return;


        var header = {
            returnDate : form.record.returnDate,
            actualReturnDate : form.record.actualReturnDate,
            bookBorrowId : form.record.bookBorrowId
        };
        var details = $.map(grid.records, function(v){
            return {
                bookId:v.id,
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