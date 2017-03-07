/**
 * Created by Thanh Binh on 2/8/2017.
 */
framework.factory('InsertUser', {
    onMessageReceive: function (sender, data) {
    },
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Insert User')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('insertForm').setFieldPerRow(1)
            .addFields([
                {field: 'name', caption: 'Name', type: 'text', required: true},
                {field: 'phone', caption: 'Phone', type: 'text'},
                {field: 'address', caption: 'Address', type: 'text'},
                {field: 'idcard', caption: 'ID Number', type: 'text'},
                {field: 'birthday', caption: 'Birthday', type: 'date', required: true},
                {field: 'username', caption: 'Username', type: 'text', required: true},
                {field: 'password', caption: 'Password', type: 'text', required: true},
                {field: 'email', caption: 'Email', type: 'email', required: true},
                {field: 'roleId', caption: 'Role', type: 'popupListRole', options: {caller: self}, required: true},
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
            .setRecord({status: 1, borrowable: 1})
        ;
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({
                id: 'btnInsert',
                type: 'button',
                caption: 'Lưu',
                icon: 'fa-floppy-o',
                onClick: self.onBtnInsertClick.bind(this)
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
    onBtnInsertClick: function () {
        var self = this;
        var form = this.findElement('insertForm');
        if (!form.validate().length) {
            form.record.status = form.record.status.id;
            form.record.borrowable = form.record.borrowable.id;
            $.post('/api/User/Save', form.record, function (result) {
                framework.common.cmdResultNoti(result);
                self.sendMessage(result);
            });
        }
    },
    onBtnClearClick: function () {
        var form = this.findElement('insertForm');
        form.clear();
    }


});
