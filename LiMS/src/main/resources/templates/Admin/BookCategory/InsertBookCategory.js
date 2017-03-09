/**
 * Created by dylan on 2/16/2017.
 */
framework.factory('InsertBookCategory', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Insert BookCategory')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('insertForm').setFieldPerRow(1)
            .addFields([
                { field: 'category_name', type: 'text', required: true, caption: 'Tên thể loại' }
            ]);
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({ id: 'btnInsert', type: 'button', caption: 'Save', icon: 'fa-floppy-o', onClick:self.onBtnInsertClick.bind(this) })
            .addItem({ id: 'btnClear', type: 'button', caption:'Re-type', icon:'fa-refresh', onClick:self.onBtnClearClick.bind(this) })
        ;
        content.setName('content1').addItem(form.end()).addItem(formFooter.end());
    },
    onBtnInsertClick: function () {
        var self = this;
        var form = this.findElement('insertForm');
        //if (!form.validate().length) {
        $.post('/api/BookCategory/Save', form.record , function (result) {
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

