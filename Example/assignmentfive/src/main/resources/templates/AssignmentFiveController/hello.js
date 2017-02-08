/**
 * Created by Thanh Binh on 2/8/2017.
 */
framework.factory("hello",{
    onInitHeader:function (header) {
        header.setName('header1').setTitle(' Thêm Loại Hàng Hóa')
            .setIcon('fa-plus');
        ;
    },
    onInitContent: function (content) {
        var self = this;
        console.log(this.ViewBag);
        var form = widget.setting.form();
        form.setName('insertForm').setFieldPerRow(1)
            .addFields([
                { field: 'Ma', type: 'text', required: true, caption: "Mã" },
                { field: 'Ten', type: 'text', required: true, caption: 'Tên' }
            ]);
        var formFooter = widget.setting.toolbar();
        formFooter.setName('insertToolbar')
            .addItem({ id: 'btnInsert', type: 'button', caption: 'Lưu', icon: 'fa-floppy-o', onClick:function () {
                self.openPopup({
                    name: 'updatePopup',
                    url: '/teammgt1',
                    title: "xin chao",
                });
                alert('phuonggay');
            } })
        ;
        content.setName('content1').addItem(form.end()).addItem(formFooter.end());
    },
});