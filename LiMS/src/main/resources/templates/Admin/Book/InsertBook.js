/**
 * Created by dylan on 2/17/2017.
 */
framework.factory('InsertBook', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Insert Book')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('insertForm').setFieldPerRow(1)
            .addFields([
                { field: 'name', type: 'text', required: true, caption: 'Tên sách' },
                { field: 'publish_year', type: 'text', required: true, caption: 'Năm xuất bản' },
                { field: 'image', type: 'text', required: false, caption: 'Hình' },
                { field: 'author', type: 'text', required: true, caption: 'Tác giả' },
                { field: 'bookCategoryId', caption: 'Thể Loại', type: 'popupListBookCategory',required: true, options:{caller:self} }
            ]);
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({ id: 'btnInsert', type: 'button', caption: 'Lưu', icon: 'fa-floppy-o', onClick:self.onBtnInsertClick.bind(this) })
            .addItem({ id: 'btnClear', type: 'button', caption:'Nhập lại', icon:'fa-refresh', onClick:self.onBtnClearClick.bind(this) })
        ;
        content.setName('content1').addItem(form.end()).addItem(formFooter.end());
    },
    onBtnInsertClick: function () {
        var self = this;
        var form = this.findElement('insertForm');
        //if (!form.validate().length) {
        $.post('/api/Book/Save', form.record , function (result) {
            if(result.success)
                alertSuccess(result.message);
            else
                alert(result.message)
            self.sendMessage(result);
        });
        //}
    },
    onBtnClearClick: function () {
        var form = this.findElement('insertForm');
        form.clear();
    }


});