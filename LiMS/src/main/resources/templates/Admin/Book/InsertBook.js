/**
 * Created by dylan on 2/17/2017.
 */
framework.factory('InsertBook', {
    onInitHeader: function (header) {
        header.setName('header1').setTitle(' Insert Book')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('insertForm').setFieldPerRow(1)
            .addFields([
                {field: 'name', type: 'text', required: true, caption: 'Book Name'},
                {field: 'publish_year', type: 'number', required: true, caption: 'Publishing year'},
                {
                    field: 'image', type: 'file', required: false, caption: 'Image',
                    options: {
                        max: 1,
                    }
                },
                {field: 'author', type: 'text', required: true, caption: 'Author'},
                {field: 'publisher', type: 'text', required: true, caption: 'Publisher'},
                {field: 'bookCode', type: 'text', required: true, caption: 'Book Code'},
                {
                    field: 'bookCategoryId',
                    caption: 'Category',
                    type: 'popupListBookCategory',
                    required: true,
                    options: {caller: self}
                },
                {
                    field: 'bookStatusId',
                    caption: 'Status',
                    type: 'popupListBookStatus',
                    required: true,
                    options: {caller: self}
                }
            ]);
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({
                id: 'btnInsert',
                type: 'button',
                caption: 'Save',
                icon: 'fa-floppy-o',
                onClick: self.onBtnInsertClick.bind(this)
            })
            .addItem({
                id: 'btnClear',
                type: 'button',
                caption: 'Re-type',
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
            if (form.record.image) {
                framework.common.requestUploadImages({
                    listImages: form.record.image,
                    successHandler: function (result) {
                        framework.common.cmdResultNoti(result);
                        if (result.success) {
                            form.record.imageUrl = result.data;
                            form.record.image = null;
                            $.ajax({
                                url: "/api/Book/Save",
                                type: "POST",
                                data: JSON.stringify(form.record),
                                success: function (result) {
                                    framework.common.cmdResultNoti(result);
                                    self.sendMessage(result);
                                },
                                dataType: "json",
                                contentType: "application/json"
                            });
                        }
                    }.bind(self)
                });
            }
            else {
                form.record.image = null; //link anh mac dinh
                $.ajax({
                    url: "/api/Book/Save",
                    type: "POST",
                    data: JSON.stringify(form.record),
                    success: function (result) {
                        framework.common.cmdResultNoti(result);
                        self.sendMessage(result);
                    },
                    dataType: "json",
                    contentType: "application/json"
                });
            }
        }

    },
    onBtnClearClick: function () {
        var form = this.findElement('insertForm');
        form.clear();
    }
});