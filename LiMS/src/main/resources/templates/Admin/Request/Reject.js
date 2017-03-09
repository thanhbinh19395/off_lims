/**
 * Created by dylan on 2/19/2017.
 */
framework.factory('Reject', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Reject Request')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        var form = widget.setting.form();
        form.setName('updateForm').setFieldPerRow(1)
            .addFields([
                { field: 'message', type: 'text', required: true, caption: 'Tin nhắn' }
            ])
            .setRecord(this.ViewBag.email)
        ;
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({ id: 'btnInsert', type: 'button', caption: 'Gửi', icon: 'fa-floppy-o', onClick:self.onBtnUpdateClick.bind(this) })
            .addItem({ id: 'btnClear', type: 'button', caption:'Re-type', icon:'fa-refresh', onClick:self.onBtnClearClick.bind(this) })
        ;
        content.setName('content1').addItem(form.end()).addItem(formFooter.end());
    },
    onBtnUpdateClick: function () {
        var self = this;
        var form = this.findElement('updateForm');
        if (!form.validate().length) {
            $.post('/api/Request/Reject', form.record , function (result) {
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