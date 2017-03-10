/**
 * Created by Thanh Binh on 3/6/2017.
 */

framework.factory('ViewBookBorrow', {
    onInitHeader: function (header) {
        header.setWidth('700px').setTitle('Xem phiếu mượn sách').setIcon('fa fa-list');

    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var bb = this.ViewBag.bookBorrow.data;
        var formData = bb.user;
        formData.createdUsername = '[' + bb.created_by.username + ']' + bb.created_by.name;
        formData.returnDate = bb.returnDate



        var form = widget.setting.form();
        form.setName('form')
            .setFieldPerRow(2)
            .addFields([
                { field: 'createdUsername', caption: 'Created By', type: 'text', html:{attr:{disabled:'disabled'}}, span : 1},
                { type: 'empty'},
                { field: 'username', caption: 'Borrowed By', type: 'text',span :1, required : true },
                { type: 'empty'},
                { field: 'name', caption: 'Name', type: 'text'},
                { field: 'phone', caption: 'Phone', type: 'text'},
                { field: 'address', caption: 'Address' , type: 'text'},
                { field: 'email', caption: 'Email' , type: 'text'},
                { field: 'idcard', caption: 'ID Number', type: 'text' },
                { field: 'birthday', caption: 'Birthday', type: 'date' },
                { field: 'returnDate', caption: 'Returned Date', type: 'date', span : 2, required : true},
            ])
            .setRecord(formData)
        ;
        var toolbar = widget.setting.toolbar();
        toolbar.setName('toolbar')
            .addItem({
                type: 'button', id: 'back', caption: 'Back', icon: 'fa-list',
                onClick: self.onBtnBackClick.bind(this)
            })
        ;
        var grid = widget.setting.grid();

        grid.setName('grid')//.setProperty({ show: {} })
            .setHeight('600px')
            .setIdColumn('id')
            .addColumns([
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
            .setRecords($.map(this.ViewBag.bookBorrow.data.bookBorrowDetails,function(v){return v.book;}))
        ;
        console.log(this.ViewBag.bookBorrow.data.bookBorrowDetails);
        content.setWidth('700px').addItem(form.end()).addItem(toolbar.end()).addItem(grid.end());
    },
    onBtnBackClick: function () {
        if(this.parentId){
            this.close();
        }
        else{
            window.location.replace("/Admin/BookBorrow/ListBookBorrow");
        }
    },
});