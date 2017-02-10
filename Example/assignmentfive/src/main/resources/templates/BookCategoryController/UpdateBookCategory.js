/**
 * Created by WIN8.1 on 10/02/2017.
 */
/**
 * Created by Thanh Binh on 2/9/2017.
 */

framework.factory('UpdateBookCategory', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Update Book Category')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('updateForm').setFieldPerRow(1)
            .addFields([
                { field: 'category_name', type: 'text', required: true, caption: 'Tên loại' }
            ])
            .setRecord(this.ViewBag.bookCategory)
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
            $.post('/api/BookCategory/Save', form.record , function (data) {
                alertSuccess('Updated !');
                self.sendMessage(data);
            });
        }
    },
    onBtnClearClick: function () {
        var form = this.findElement('updateForm');
        form.clear();
    }


});
