/**
 * Created by Thanh Binh on 2/9/2017.
 */

framework.factory('UpdateCountry', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Update Country')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('updateForm').setFieldPerRow(1)
            .addFields([
                { field: 'countryname', type: 'text', required: true, caption: 'Tên' }
            ])
            .setRecord(this.ViewBag.country)
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
            $.post('/api/Country/Save', form.record , function (data) {
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
