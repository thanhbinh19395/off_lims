/**
 * Created by Thanh Binh on 2/8/2017.
 */
framework.factory('InsertRole', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Insert Role')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('insertForm').setFieldPerRow(1)
            .addFields([
                { field: 'name', type: 'text', required: true, caption: 'Tên vai trò' }
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
        if (!form.validate().length) {
            $.post('/api/Role/Save', form.record , function (data) {
                alertSuccess('Inserted !');
                self.sendMessage(data);
            });
        }
    },
    onBtnClearClick: function () {
        var form = this.findElement('insertForm');
        form.clear();
    }


});
