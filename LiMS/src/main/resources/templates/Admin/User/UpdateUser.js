/**
 * Created by Thanh Binh on 2/9/2017.
 */

framework.factory('UpdateUser', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Update User')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('updateForm').setFieldPerRow(1)
            .addFields([
                {field: 'name', caption: 'Name', type: 'text', required: true},
                {field: 'phone', caption: 'Phone', type: 'text'},
                {field: 'address', caption: 'Address', type: 'text'},
                {field: 'idcard', caption: 'ID Number', type: 'text'},
                {field: 'birthday', caption: 'Birthday', type: 'date', required: true},
                {field: 'username', caption: 'Username', type: 'text', required: true},
                {field: 'password', caption: 'Password', type: 'text', required: true},
                {field: 'email', caption: 'Email', type: 'email', required: true},
                {
                    field: 'roleId',
                    caption: 'Role',
                    type: 'popupListRole',
                    options: {caller: self, data: self.ViewBag.user.data.role},
                    required: true
                },
                {
                    field: 'status', type: 'list', required: true, caption: 'Status', options: {
                    items: [
                        {id: 0, text: 'Deactive'},
                        {id: 1, text: 'Active'},
                    ]
                },
                },
                {
                    field: 'borrowable', type: 'list', required: true, caption: 'Borrowable', options: {
                    items: [
                        {id: 0, text: 'Disable'},
                        {id: 1, text: 'Enable'},
                    ]
                },
                }
            ])
            .setRecord(this.ViewBag.user.data)
        ;
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({
                id: 'btnInsert',
                type: 'button',
                caption: 'Lưu',
                icon: 'fa-floppy-o',
                onClick: self.onBtnUpdateClick.bind(this)
            })
            .addItem({
                id: 'btnClear',
                type: 'button',
                caption: 'Nhập lại',
                icon: 'fa-refresh',
                onClick: self.onBtnClearClick.bind(this)
            })
        ;
        content.setName('content1').addItem(form.end()).addItem(formFooter.end());
    },
    onBtnUpdateClick: function () {
        var self = this;
        var form = this.findElement('updateForm');
        delete form.record.role;
        if (!form.validate().length) {
            $.post('/api/User/Save', form.record, function (result) {
                framework.common.cmdResulNoti(result);
                self.sendMessage(result);
            });
        }
    },
    onBtnClearClick: function () {
        var form = this.findElement('updateForm');
        form.clear();
    }


});
