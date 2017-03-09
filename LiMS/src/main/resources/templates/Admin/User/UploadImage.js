/**
 * Created by Thanh Binh on 2/25/2017.
 */
framework.factory('UploadImage', {
    onMessageReceive:function(sender, data){
        debugger
    },
    onInitHeader: function (header) {
        header.setName('header').setTitle(' Insert User')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('insertForm').setFieldPerRow(1)
            .addFields([
                { field: 'model', caption: 'Name', type: 'file'},
            ])
            //.setRecord({status : 1})
        ;
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({ id: 'btnInsert', type: 'button', caption: 'Save', icon: 'fa-floppy-o', onClick:self.onBtnInsertClick.bind(this) })
            .addItem({ id: 'btnClear', type: 'button', caption:'Re-type', icon:'fa-refresh', onClick:self.onBtnClearClick.bind(this) })
        ;
        content.setName('content').addItem(form.end()).addItem(formFooter.end());
    },
    onBtnInsertClick: function () {
        var self = this;
        var form = this.findElement('insertForm');
       // if (!form.validate().length) {
            //form.record.status = form.record.status.id;

        this.requestUploadImage('binh',form.record.model)
        //}
    },
    onBtnClearClick: function () {
        var form = this.findElement('insertForm');
        form.clear();
    },

    requestUploadImage: function (id, listImage, successHandler) {
        var self = this;
        $.each(listImage, function (k, v) {
            var data = new FormData();
            data.append('image', v.file);
            $.ajax({
                url: '/api/Image/Store',
                type: "POST",
                enctype: 'multipart/form-data',
                processData: false,
                contentType: false,
                data: data,
                success: function (response) {
                    console.log(response);
                    successHandler && successHandler.call(self, response);
                },
                error: function (er) {
                    console.log(er);
                }
            });
        });
    }

});
