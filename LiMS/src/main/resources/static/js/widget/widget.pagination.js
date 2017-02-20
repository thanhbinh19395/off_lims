$.widget('widget.pagination', $.widget.base, {
    options: {
        name: null,
        totalPages: 1,
        startPage: 1,
        position: 'left',
        first: "&laquo;",
        prev: "&lsaquo;",
        next: "&rsaquo;",
        last: "&raquo;",
    },
    _create: function () {
        this.pagi = $('<ul>')
                    .addClass('pagination')
                    .addClass('pagination-sm')
                    //.addClass('pull-right')
                    .css('padding', '3px 3px 0 0')
        ;
        this.element.addClass('text-' + this.options.position).append(this.pagi);
        this.options.startPage = this.options.startPage + 1;
        this.options.totalPages = this.options.totalPages == 0 ? 1 : this.options.totalPages;
        var pagination = this.pagi.twbsPagination(this.options);

        this._super();

        $.extend(pagination, {
            reset: function (page, total) {
                self.reset(page, total);
            }
        });
        //console.log(pagination);
        this._saveData(pagination);
        var self = this;

    },
    reset: function (page, total) {
        if (this.options.totalPages != total) {

            if (this.pagi.data("twbs-pagination")) {
                //console.log('destroy');
                this.pagi.twbsPagination('destroy');
            }
            this.options.totalPages = total != 0 ? total : 1;
            this.options.startPage = page+1;
            //console.log(this.options);
            this.pagi.twbsPagination(this.options);
        }
    }
});