/**
 * Created by dylan on 2/17/2017.
 */

framework.factory('UpdateBookStatus', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Update BookStatus')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('updateForm').setFieldPerRow(1)
            .addFields([
                { field: 'description', type: 'text', required: true, caption: 'Mô tả' }
            ])
            .setRecord(this.ViewBag.BookStatus.data)
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
            $.post('/api/BookStatus/Save', form.record , function (result) {
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
