$().w2field('addType', 'popupListRole', function (options) {
    var self = this;

    $(this.el).css('width', '30%').attr('disabled', 'disabled');
    var input;
    if ($(self.el).parent().find('.inputSearch').length) {
        input = $(self.el).parent().find('.inputSearch');
        input.val('');
        input.off('keydown');
    }
    else {
        input = $('<input>').css({
            width: '65%',
            border: '0px'
        }).attr('placeholder', 'Tìm theo tên').addClass('inputSearch');
        $(this.el).parent().append(input);
    }
    if (options.data) {
        input.val(options.data.name);
    }
    else {
        input.val('');
    }
    //this sau khi extend sẽ được chứa trong $(this.el).data('w2field')
    $.extend(self, {
        onMessageReceive: function (sender, message) {
            if (sender.pageName == 'ListRole')
            {
                $(self.el).val(message.id);
                $(self.el).change();
                $(input).val(message.name)
                $(self.el).data('data', message);
                sender.close();
            }
        }
    });

    var data = {
        param: {},
        name: $(self.el).attr('name'),
        pageName: 'ListRole',
        id: $(self.el).attr('name'),
        $el: self.el,
        field: self,
        eventType: ''
    }

    var buttonSearch, buttonRemove;
    if ($(self.el).parent().find('.buttonSearch').length) {
        buttonSearch = $(self.el).parent().find('.buttonSearch');
    }
    else {
        buttonSearch = $('<button>')
            .attr('style', 'margin-left:auto !important; font-size:small;')
            .addClass('input-icon buttonSearch')
            .append($('<span>').addClass('fa fa-search'));
        buttonSearch.insertAfter(self.el);
    }
    if ($(self.el).parent().find('.buttonRemove').length) {
        buttonRemove = $(self.el).parent().find('.buttonRemove');
    }
    else {
        buttonRemove = $('<button>')
            .attr('style', 'font-size:small;')
            .addClass('input-icon buttonRemove')
            .append($('<span>').addClass('fa fa-times'));
        buttonRemove.appendTo($(self.el).parent());
    }

    buttonSearch.click(function () {
        if (options.caller) {
            data.eventType = 'open';
            options.caller.onPopupHandler && options.caller.onPopupHandler(data);
        }
        $.extend(data.param, {name : input.val()});
        $.post('/api/Role/FindByNameContaining', data.param, function (result) {
            var records = result.data;
            if (records.length == 0) {
                alert("Role Not found !");
                return;
            }
            if (records.length == 1) {
                self.onMessageReceive(data, records[0]);
                options.caller.onMessageReceive(data, records[0]);
                return;
            }
            else {
                options.caller.openPopup({
                    name: self.type,
                    url: '/Admin/Role/ListRole',
                    width: data.width || (options.width || 600),
                    height: data.height || (options.height || 'auto'),
                    resizable: true

                }, data.param);
            }
        });

    });
    buttonRemove.click(function () {
        self.set(null);
        input.val('');
        $(self.el).change();
        if (options.caller) {
            data.eventType = 'remove';
            options.caller.onPopupHandler && options.caller.onPopupHandler(data);
        }
        $(self.el).data('data', null);
    });
    input.on('keydown', function (e) {
        if (e.which == 13) {
            buttonSearch.click();
        }
    })
    buttonRemove.appendTo($(self.el).parent());
    buttonSearch.insertAfter(self.el);
});