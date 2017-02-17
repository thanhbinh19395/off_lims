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
                { field: 'name', type: 'text', required: true, caption: 'Tên Sách' },
                { field: 'publish_year', type: 'text', required: true, caption: 'Năm xuất bản' },
                { field: 'image', type: 'text', required: false, caption: 'Hình' },
                { field: 'bookCategoryId', type: 'text', required: true, caption: 'Thể loại' },
                { field: 'bookStatusId', type: 'text', required: true, caption: 'Trạng thái' }
            ])
            .setRecord(this.ViewBag.Book.data)
        ;
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({ id: 'btnInsert', type: 'button', caption: 'Lưu', icon: 'fa-floppy-o', onClick:self.onBtnUpdateClick.bind(this) })
            .addItem({ id: 'btnClear', type: 'button', caption:'Nhập lại', icon:'fa-refresh', onClick:self.onBtnClearClick.bind(this) })
        ;
        content.setName('content1').addItem(form.end()).addItem(formFooter.end());
    },
    onBtnUpdateClick: function () {
        var self = this;
        var form = this.findElement('updateForm');
        if (!form.validate().length) {
            delete form.record.bookStatus;
            delete form.record.bookCategory;
            delete form.record.bookBorrowDetail;
            $.post('/api/Book/Save', form.record , function (result) {
                if(result.success)
                    alertSuccess(result.message);
                else
                    alert(result.message)
                self.sendMessage(result);
            });
        }
    },
    onBtnClearClick: function () {
        var form = this.findElement('updateForm');
        form.clear();
    }


});
