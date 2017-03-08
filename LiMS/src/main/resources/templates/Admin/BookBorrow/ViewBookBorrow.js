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
                { field: 'createdUsername', caption: 'Người lập', type: 'text', html:{attr:{disabled:'disabled'}}, span : 1},
                { type: 'empty'},
                { field: 'username', caption: 'Người mượn', type: 'text',span :1, required : true },
                { type: 'empty'},
                { field: 'name', caption: 'Name', type: 'text'},
                { field: 'phone', caption: 'Phone', type: 'text'},
                { field: 'address', caption: 'Address' , type: 'text'},
                { field: 'email', caption: 'Email' , type: 'text'},
                { field: 'idcard', caption: 'ID Number', type: 'text' },
                { field: 'birthday', caption: 'Birthday', type: 'date' },
                { field: 'returnDate', caption: 'Hạn trả', type: 'date', span : 2, required : true},
            ])
            .setRecord(formData)
        ;
        var toolbar = widget.setting.toolbar();
        toolbar.setName('toolbar')
            .addItem({
                type: 'button', id: 'back', caption: 'Trở lại danh sách', icon: 'fa-list',
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
                { field: 'name', caption: 'Tên Sách', size: '30%', sortable: true, resizable: true },
                { field: 'publish_year', caption: 'Năm Xuất Bản', size: '10%', sortable: true, resizable: true },
                { field: 'author', caption: 'Tác giả', size: '10%', sortable: true, resizable: true },
                { field: 'bookCategory.category_name', caption: 'Thể loại', size: '15%', sortable: true, resizable: true },
                { field: 'bookStatus.description', caption: 'Trạng Thái', size: '15%', sortable: true, resizable: true }
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
            window.location.replace("/Admin/BookBorrowHeader/ListBookBorrowHeader");
        }
    },
});