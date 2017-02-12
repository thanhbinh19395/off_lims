$.widget('widget.grid', $.widget.base, {
    options: {
        name: null
    },
    _create: function () {
        var self = this;

        if (this.options.paginateOptions) {
            var pagiName = 'pagination' + '_' + this.options.name + this.options.paginateOptions.name || '';
            this.options.toolbar.items.push({ type: 'spacer'});
            this.options.toolbar.items.push({
                type: 'html',
                id: pagiName + '_id',
                html: '<div class="pagination pull-right" style="width: 330px; margin-right:-5px;"><ul id="' + pagiName + '" class="pull-right "></ul></div>'
            });
        };
        var recidArr = this.options.recid;
        if ($.isArray(recidArr)) {
            
            self.addRecid(this.options.data,recidArr);
            this.options.recid = null;
        }
        var grid = this.element.w2grid(this.options);
        this.options.data && grid.add(this.options.data);

        if ($.isArray(recidArr)) {
            var gridAdd = grid.add.bind(grid);
            $.extend(grid, {
                add: function (records) {
                    self.addRecid(records, recidArr);
                    gridAdd(records);
                }
            });
        }

        if (this.options.paginateOptions) {
            var pagi = this.element.find('#' + pagiName).pagination(this.options.paginateOptions);
            var pagiData = pagi.data("widget-pagination").options.__data;
            $.extend(grid, { pagination: pagiData });
        }
        
        this._super();
        this._saveData(grid);
    },
    addRecid: function (records, recidArr) {
        $.each(records || [], function (keyData, valData) {
            var recid = [];
            $.each(recidArr || [], function (key, val) {
                recid.push(valData[val]);
            });
            $.extend(valData, { recid: recid.join('_') });
        });
    }
});