/**
 * Created by dylan on 2/23/2017.
 */
/**
 * Created by dylan on 2/23/2017.
 */
framework.factory('CreateSO', {
    onPopupHandler: function (data) {
        if (data.eventType == 'remove') {
            var form = this.findElement('form');
            form.record.returnDate = null;
            form.record.bookTransaction = null;
            form.refresh();
        }
    },
    onMessageReceive: function (sender, data) {
        if (sender.pageName == 'ListBook') {
            this.insertSODetailHandler(sender, data.data);
        }
        else if (sender.pageName == 'insertProduct') {
            data.data.ProductCode = data.data.Code;
            data.data.ProductName = data.data.Name;
            this.insertSODetailHandler(sender, data.data);
        }
        else if (sender.pageName == 'ListCustomer') {
            var form = this.findElement('form');
            form.record.DebitInShop = data.DebitInShop;
            form.record.CustomerCode = data.Code;
            form.record.CustomerName = data.Name;
            form.record.CustomerPhone = data.Phone;
            form.record.CustomerAddress = data.Address;
            form.record.CustomerEmail = data.Email;
            form.refresh();
            sender.close && sender.close();
        }
    },
    onInitHeader: function (header) {
        header.setWidth('700px').setTitle('Tạo mới phiếu mượn sách').setIcon('fa fa-list');

    },
    onInitContent: function (content) {
        var self = this;

        var form = widget.setting.form();

        form.setName('form')
            .setFieldPerRow(2)
            .addFields([
                { field: 'userId', caption: 'UserId', type: 'popupListUser',options:{caller:self} },
                { field: 'returnDate', caption: 'Ngày trả sách', type: 'date'}
            ])
            //.setRecord(self.ViewBag.listBookBorrowHeader.data)
            .createEvent('onChange', self.onChangeForm.bind(this))
        ;
        var toolbar = widget.setting.toolbar();
        toolbar.setName('toolbar')
            .addItem({
                type: 'button', id: 'back', caption: 'Trở lại danh sách', icon: 'fa-list',
                onClick: self.onBtnBackClick.bind(this)
            })
            .addItem({
                type: 'button', id: 'save', caption: 'Lưu', icon: 'glyphicon glyphicon-floppy-saved',
                onClick: self.onBtnSaveClick.bind(this)
            })
            .addItem({
                type: 'button', id: 'saveInventory', caption: 'Kho', icon: 'glyphicon glyphicon-log-in',
                onClick: self.onBtnSaveInventoryClick.bind(this)
            })
            .addItem({
                type: 'button', id: 'saveFinish', caption: 'Thanh toán', icon: 'glyphicon glyphicon-ok',
                onClick: self.onBtnSaveFinishClick.bind(this)
            })
            .addItem({ type: 'spacer' })
            .addItem({
                type: 'html', id: 'item5',
                html: function (item) {
                    var html =
                        '<div style="padding: 3px 10px;">' +
                        ' Product:' +
                        '    <input id="product" size="20" placeholder="Name, Code, Barcode" ' +
                        '         style="padding: 3px; border-radius: 2px; border: 1px solid silver" />' +
                        '</div>';
                    return html;
                }
            })
            .createEvent('onRender', function (event) {
                event.done(function (e) {
                    var inputProduct = $(e.box).find('#product');
                    inputProduct.dblclick(function (dce) {
                        self.onChooseProductClick();
                    });
                    inputProduct.keypress(function (kpe) {
                        if (kpe.key == 'Enter') {
                            var val = $(kpe.target).val();
                            $.post('/api/Book/GetList', {
                                name: val,
                            }, function (result) {
                                console.log(result);
                                var data = JSON.parse(result.data.data);
                                if (data.length == 0) {
                                    alert('Product Not found !');
                                }
                                else if (data.length == 1) {
                                    self.insertSODetailHandler(null, data[0]);
                                }
                                else {
                                    self.openPopup({
                                        name: 'insertPopup',
                                        url: '/Admin/Book/ListBook',
                                        width: 600
                                    }, {   name: val,}  );
                                }
                            });
                            $(kpe.target).val(null);
                        }
                    });
                });
            });
        ;
        var grid = widget.setting.grid();

        grid.setName('grid').setProperty({ show: {} })
            .setHeight('600px')
            .setIdColumn('id')
            .addColumns([
                { field: 'id', caption: 'Mã sản phẩm', size: '10%', resizable: true, sortable: true },
                { field: 'name', caption: 'Tên Sách', size: '30%', sortable: true, resizable: true },
                { field: 'publish_year', caption: 'Năm Xuất Bản', size: '10%', sortable: true, resizable: true },
                { field: 'author', caption: 'Tác giả', size: '10%', sortable: true, resizable: true },
                { field: 'image', caption: 'Hình', size: '15%', sortable: true, resizable: true },
                { field: 'bookCode', caption: 'Book Code', size: '15%', sortable: true, resizable: true },
                { field: 'bookCategory.category_name', caption: 'Thể loại', size: '15%', sortable: true, resizable: true },
                { field: 'bookStatus.description', caption: 'Trạng Thái', size: '15%', sortable: true, resizable: true }
            ])
            .addButton('delete', 'Xóa', 'fa fa-times', self.onBtnDeleteClick.bind(self))
            .addButton('product', 'Chọn', 'fa fa-check', self.onChooseProductClick.bind(self))
            .addButton('insertProduct', 'Thêm mới', 'fa fa-plus', self.onInsertProductClick.bind(self))
            .createEvent('onChange', self.onEditFieldGrid.bind(self)).createEvent('onSearch', self.onSearchProductGrid.bind(self))
        ;



        content.setWidth('700px').addItem(form.end()).addItem(toolbar.end()).addItem(grid.end());
    },
    onBtnBackClick: function () {
        window.location.replace("/Admin/BookBorrowHeader/ListBookBorrowHeader");
    },
    onSearchProductGrid: function (e) {
        console.log(e);
    },
    onBtnSaveInventoryClick: function () {
        var curSO = this.getCurrentSO();
        if (curSO) {
            $.post('/api/BookBorrowHeader/Save', {header: this.form.record, detail: this.w2ui.grid.record}, function (data) {
                framework.common.cmdResultNoti(data);
            });
        }
    },
    onBtnSaveFinishClick: function () {
        var curSO = this.getCurrentSO();
        var self = this;
        var form = this.findElement('form');
        var grid = this.findElement('grid');
        console.log({header: form.record, detail:  grid.records});
        if (curSO) {

            $.post('/api/BookBorrowHeader/Save', {header: form.record, detail:  grid.records}, function (data) {
                framework.common.cmdResultNoti(data);
            });
        }
    },
    onBtnSaveClick: function () {
        var curSO = this.getCurrentSO();
        if (curSO) {
            $.post('/api/BookBorrowHeader/Save', {header: form.record, detail: w2ui.grid.records}, function (data) {
                framework.common.cmdResultNoti(data);
            });
        }
    },
    onChangeForm: function (e) {
        if (e.target == 'CustomerPaid') {
            var self = this;
            e.done(function () {
                self.updateCharge(e.value_new);
            });

        }
    },
    onBtnDeleteClick: function (e) {
        var grid = this.findElement('grid');
        grid.delete(true);
        this.updateTotal();
    },
    onEditFieldGrid: function (e) {
        var self = this;
        e.done(function () {
            self.refreshGrid(e.recid);
        });
    },
    refreshGrid: function (recid) {
        var grid = this.findElement('grid');
        grid.mergeChanges();
        if (recid)
            grid.refresh(recid);
        else
            grid.refresh();
        this.updateTotal();
    },
    updateTotal: function () {
        var form = this.findElement('form');
        //form.record.Total = this.calculateTotal();
        form.refresh();
    },
    updateCharge: function (paid) {
        var form = this.findElement('form');
        form.record.Charge = paid - form.record.Total;
        form.refresh();
    },
    calculateTotal: function () {
        var grid = this.findElement('grid');
        var total = 0;
        $.each(grid.records, function (k, v) {
            total += v.ProductPrice * v.Quantity;
        });
        return total;
    },
    insertSODetailHandler: function (sender, data) {
        var grid = this.findElement('grid');
        var existBBDetail = grid.get(data.id);
        if (existBBDetail) {
            alert("ko dc trung sach");
        }
        else {
            grid.add(data);
        }
        this.updateTotal();
    },

    onInsertProductClick: function () {
        this.openPopup({
            name: 'insertPopup',
            url: '/  /ProductHandler/InsertProduct',
            width: 600
        });
    },
    onChooseProductClick: function () {
        this.openPopup({
            name: 'insertPopup',
            url: '/Admin/Book/ListBook',
            width: 600
        });
    },
    getCurrentSO: function () {
        var form = this.findElement('form');
        if (form.validate() != 0 || form.record.Total == 0)
            return;

        var grid = this.findElement('grid');
        return {
            SOHeader: form.record,
            SoDetails: grid.records
        }
    },
    toInitState: function () {
        var form = this.findElement('form');
        var grid = this.findElement('grid');
        grid.clear();
        form.clear();
    },
    onLoadComplete: function () {
        this.$contentEl.find('#product').focus();
    },
});