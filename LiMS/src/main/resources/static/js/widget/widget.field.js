$().w2field('addType', 'popupListCategory', function (options) {
    var self = this;
    $(this.el).css('width', '30%').attr('disabled', 'disabled');
    var input = $('<input>').css({
        width: '65%',
        border: '0px'
    });
    
    if (!$.isEmptyObject(options.data) && options.data.Name) {
        input.val(options.data.Name);
    }
    $(this.el).parent().append(input);
    //this sau khi extend sẽ được chứa trong $(this.el).data('w2field')
    $.extend(self, {
        onMessageReceive: function (sender, message) {
            $(self.el).val(message.CategoryId);
            $(self.el).change();
            $(input).val(message.Name)
            $(self.el).data('data', message);
        }
    });

    var data = {
        param: {},
        name: $(self.el).attr('name'),
        type: self.type,
        id: $(self.el).attr('name'),
        $el: self.el,
        field: self,
        eventType: ''
    }
    console.log($(self.el).parent().data());
    var buttonSearch = $('<button>')
        .attr('style', 'margin-left:auto !important; font-size:small;')
        .addClass('input-icon')
        .append($('<span>').addClass('fa fa-search'));
    var buttonRemove = $('<button>')
        //.attr('style', 'margin-left:auto !important; font-size:small;')
        .addClass('input-icon')
        .append($('<span>').addClass('fa fa-times'));

    buttonSearch.click(function () {
        if (options.caller) {
            data.eventType = 'open';
            options.caller.onPopupHandler && options.caller.onPopupHandler(data);
        }
        $.extend(data.param, {
            FieldName: $(self.el).attr('name'),
            SearchOnOpen: true,
            CommandAction: {
                Name: input.val(),
                Or: false,
                currentPage: 1
            }
        });
        $.post('/Documents/Category/Search', {
            CommandAction: data.param.CommandAction
        }, function (result) {
            var records = JSON.parse(result.Data);
            if (records.length == 0) {
                alert("không có loại hàng hóa cần tìm");
                return;
            }

            if (records.length == 1) {
                self.onMessageReceive(null, records[0]);
                return;
            }
            else {
                options.caller.openPopup({
                    name: 'themPopup',
                    url: '/Documents/Category/ListCategory',
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
