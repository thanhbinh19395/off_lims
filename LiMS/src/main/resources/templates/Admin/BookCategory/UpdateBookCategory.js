/**
 * Created by dylan on 2/16/2017.
 */
framework.factory('UpdateBookCategory', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Update BookCategory')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('updateForm').setFieldPerRow(1)
            .addFields([
                { field: 'category_name', type: 'text', required: true, caption: 'tên thể loại' }
            ])
            .setRecord(this.ViewBag.bookcategory.data)
        ;
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({ id: 'btnInsert', type: 'button', caption: 'Save', icon: 'fa-floppy-o', onClick:self.onBtnUpdateClick.bind(this) })
            .addItem({ id: 'btnClear', type: 'button', caption:'Re-type', icon:'fa-refresh', onClick:self.onBtnClearClick.bind(this) })
        ;
        content.setName('content1').addItem(form.end()).addItem(formFooter.end());
    },
    onBtnUpdateClick: function () {
        var self = this;
        var form = this.findElement('updateForm');
        if (!form.validate().length) {
            $.ajax({
                url: "/api/BookCategory/Save",
                type: "POST",
                data: JSON.stringify(form.record),
                success: function (r) {
                    framework.common.cmdResultNoti(r);
                    self.sendMessage(result);
                },
                dataType: "json",
                contentType: "application/json"
            });
        }
    },
    onBtnClearClick: function () {
        var form = this.findElement('updateForm');
        form.clear();
    }


});
