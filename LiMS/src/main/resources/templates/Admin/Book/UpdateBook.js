/**
 * Created by dylan on 2/17/2017.
 */
framework.factory('UpdateBook', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Update Book')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        console.log(this.ViewBag.Book.data);
        form.setName('updateForm').setFieldPerRow(1)
            .addFields([
                { field: 'name', type: 'text', required: true, caption: 'Book Name' },
                { field: 'publish_year', type: 'text', required: true, caption: 'Publishing Year' },
                { field: 'image', type: 'text', required: false, caption: 'Image' },
                { field: 'author', type: 'text', required: true, caption: 'Author' },
                { field: 'state', type: 'text', required: true, caption: 'Status' },
                { field: 'publisher', type: 'text', required: true, caption: 'Nhà xuất bản' },
                { field: 'bookCode', type: 'text', required: true, caption: 'Book Code' },
                { field: 'bookCategoryId', caption: 'Thể Loại', type: 'popupListBookCategory',required: true, options:{caller:self} },
                { field: 'bookStatusId',caption:'Tình trạng', required: true,type: 'popupListBookStatus', options:{caller:self}  }
            ])
            .setRecord(this.ViewBag.Book.data)
        ;
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({ id: 'btnInsert', type: 'button', caption: 'Save', icon: 'fa-floppy-o', onClick:self.onBtnUpdateClick.bind(this) })
            .addItem({ id: 'btnClear', type: 'button', caption:'Re-type', icon:'fa-refresh', onClick:self.onBtnClearClick.bind(this) })
        ;
        content.setName('content1').addItem(form.end()).addItem(formFooter.end());
    },
    onBtnUpdateClick: function () {
        var self = this;
        var form = this.findElement('updateForm');
        console.log(form.record);
        if (!form.validate().length) {
            delete form.record.bookStatus;
            delete form.record.bookCategory;
            delete form.record.bookBorrowDetail;
            $.ajax({
                url: "/api/Book/Save",
                type: "POST",
                data: JSON.stringify(form.record),
                success: function (result) {
                    framework.common.cmdResultNoti(result);
                    self.sendMessage(result);
                },
                dataType: "json",
                contentType: "application/json"
            });
        }
    },
    onBtnClearClick: function () {
        var form = this.findElement('updateForm');
        form.clear();
    }


});
