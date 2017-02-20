/**
 * Created by Thanh Binh on 2/8/2017.
 */
framework.factory('InsertUser', {
    onMessageReceive:function(sender, data){
        debugger
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
                { field: 'name', caption: 'Name', type: 'text'},
                { field: 'phone', caption: 'Phone', type: 'text'},
                { field: 'address', caption: 'Address' , type: 'text'},
                { field: 'idcard', caption: 'ID Number', type: 'text' },
                { field: 'birthday', caption: 'Birthday', type: 'date' },
                { field: 'username', caption: 'Username' , type: 'text'},
                { field: 'password', caption: 'Password', type: 'text' },
                { field: 'email', caption: 'Email', type: 'text' },
                { field: 'roleId', caption: 'Role', type: 'popupListRole', options:{caller:self} },
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
            $.post('/api/User/Save', form.record , function (result) {
                if(result.success)
                    alertSuccess(result.message);
                else
                    alert(result.message)
                self.sendMessage(result);
            });
        }
    },
    onBtnClearClick: function () {
        var form = this.findElement('insertForm');
        form.clear();
    }


});
