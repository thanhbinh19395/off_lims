/* popUp ListRole*/
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
            //1
        }).attr('placeholder', 'Tìm theo tên').addClass('inputSearch');
        $(this.el).parent().append(input);
    }
    if (options.data) {
        //2
        input.val(options.data.name);
    }
    else {
        input.val('');
    }
    //this sau khi extend sẽ được chứa trong $(this.el).data('w2field')
    $.extend(self, {
        onMessageReceive: function (sender, message) {
            //3
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
        //4
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
        $.post('/api/Role/Search', data.param, function (result) {
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
                    //page list ấy
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
/* popUp ListBookCategory*/
$().w2field('addType', 'popupListBookCategory', function (options) {
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
            //1
        }).attr('placeholder', 'Tìm theo tên thể loại').addClass('inputSearch');
        $(this.el).parent().append(input);
    }
    if (options.data) {
        //2
        input.val(options.data.category_name);
    }
    else {
        input.val('');
    }
    //this sau khi extend sẽ được chứa trong $(this.el).data('w2field')
    $.extend(self, {
        onMessageReceive: function (sender, message) {
            //3
            if (sender.pageName == 'ListBookCategory')
            {
                $(self.el).val(message.id);
                $(self.el).change();
                $(input).val(message.category_name)
                $(self.el).data('data', message);
                sender.close();
            }
        }
    });

    var data = {
        param: {},
        name: $(self.el).attr('category_name'),
        //4
        pageName: 'ListBookCategory',
        id: $(self.el).attr('category_name'),
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
        $.extend(data.param, {category_name : input.val()});
        //5
        $.post('/api/BookCategory/FindByNameContaining', data.param, function (result) {
            var records = result.data;
            if (records.length == 0) {
                alert("BookCategory Not found !");
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
                    //page list ấy
                    url: '/Admin/BookCategory/ListBookCategory',
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
/* popUp ListBookStatus*/
$().w2field('addType', 'popupListBookStatus', function (options) {
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
            //1
        }).attr('placeholder', 'Tìm theo tên trạng thái').addClass('inputSearch');
        $(this.el).parent().append(input);
    }
    if (options.data) {
        //2
        input.val(options.data.description);
    }
    else {
        input.val('');
    }
    //this sau khi extend sẽ được chứa trong $(this.el).data('w2field')
    $.extend(self, {
        onMessageReceive: function (sender, message) {
            //3
            if (sender.pageName == 'ListBookStatus')
            {
                $(self.el).val(message.id);
                $(self.el).change();
                $(input).val(message.description)
                $(self.el).data('data', message);
                sender.close();
            }
        }
    });

    var data = {
        param: {},
        name: $(self.el).attr('description'),
        //4
        pageName: 'ListBookStatus',
        id: $(self.el).attr('description'),
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
        $.extend(data.param, {description : input.val()});
        //5
        $.post('/api/BookStatus/FindByNameContaining', data.param, function (result) {
            var records = result.data;
            if (records.length == 0) {
                alert("ListBookStatus Not found !");
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
                    //page list ấy
                    url: '/Admin/BookStatus/ListBookStatus',
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
/* popUp ListBookListUser*/
$().w2field('addType', 'popupListUser', function (options) {
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
            //1
        }).attr('placeholder', 'Find by username').addClass('inputSearch');
        $(this.el).parent().append(input);
    }
    if (options.data) {
        //2
        input.val(options.data.name);
    }
    else {
        input.val('');
    }
    //this sau khi extend sẽ được chứa trong $(this.el).data('w2field')
    $.extend(self, {
        onMessageReceive: function (sender, message) {
            //3
            if (sender.pageName == 'ListUser')
            {
                data.message = message;
                $(self.el).val(message.id);
                $(self.el).change();
                $(input).val(message.username)
                $(self.el).data('data', message);
                sender.close && sender.close();
                if (options.caller) {
                    data.eventType = 'received';
                    options.caller.onPopupHandler && options.caller.onPopupHandler(data);
                }
            }
        }
    });

    var data = {
        param: {},
        name: $(self.el).attr('name'),
        //4
        pageName: 'ListUser',
        id: $(self.el).attr('id'),
        $el: self.el,
        field: self,
        eventType: '',
    }

    //var buttonSearch, buttonRemove;
    if ($(self.el).parent().find('.buttonSearch').length) {
        data.buttonSearch = $(self.el).parent().find('.buttonSearch');
    }
    else {
        data.buttonSearch = $('<button>')
            .attr('style', 'margin-left:auto !important; font-size:small;')
            .addClass('input-icon buttonSearch')
            .append($('<span>').addClass('fa fa-search'));
        data.buttonSearch.insertAfter(self.el);
    }
    if ($(self.el).parent().find('.buttonRemove').length) {
        data.buttonRemove = $(self.el).parent().find('.buttonRemove');
    }
    else {
        data.buttonRemove = $('<button>')
            .attr('style', 'font-size:small;')
            .addClass('input-icon buttonRemove')
            .append($('<span>').addClass('fa fa-times'));
        data.buttonRemove.appendTo($(self.el).parent());
    }

    data.buttonSearch.click(function () {
        if (options.caller) {
            data.eventType = 'open';
            options.caller.onPopupHandler && options.caller.onPopupHandler(data);
        }
        $.extend(data.param, {username : input.val()});
        //5
        $.post('/api/User/Search', data.param, function (result) {
            var records = result.data;
            if (records.length == 0) {
                alert("User Not found !");
                return;
            }
            if (records.length == 1) {
                options.caller.onMessageReceive(data, records[0]);
                self.onMessageReceive(data, records[0]);

                return;
            }
            else {
                options.caller.openPopup({
                    name: self.type,
                    //page list ấy
                    url: '/Admin/User/ListUser',
                    width: data.width || (options.width || 600),
                    height: data.height || (options.height || 'auto'),
                    resizable: true

                }, data.param);
            }
            if (options.caller) {
                data.eventType = 'remove';
                options.caller.onPopupHandler && options.caller.onPopupHandler(data);
            }
        });

    });
    data.buttonRemove.click(function () {
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
            data.buttonSearch.click();
        }
    })
    data.buttonRemove.appendTo($(self.el).parent());
    data.buttonSearch.insertAfter(self.el);
});
/* popUp ListBookBorrow*/
$().w2field('addType', 'popupListBookBorrow', function (options) {
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
            //1
        }).attr('placeholder', 'Tìm theo mã').addClass('inputSearch');
        $(this.el).parent().append(input);
    }
    if (options.data) {
        //2
        input.val(options.data.name);
    }
    else {
        input.val('');
    }
    //this sau khi extend sẽ được chứa trong $(this.el).data('w2field')
    $.extend(self, {
        onMessageReceive: function (sender, message) {
            //3
            if (sender.pageName == 'ListBookBorrow')
            {
                $(self.el).val(message.id);
                $(self.el).change();
                //$(input).val(message.name)
                $(self.el).data('data', message);
                //sender.close || sender.close();
            }
        }
    });

    var data = {
        param: {},
        name: $(self.el).attr('name'),
        //4
        pageName: 'ListBookBorrow',
        id: $(self.el).attr('id'),
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
        data.param = {};

        var inputVal = input.val();

        if($.isNumeric(inputVal)){
            data.param.id = inputVal;
        }
        else{
            data.param = {
                user:{
                    username:inputVal
                }
            }
        }

        if($.isEmptyObject(input.val()))
            data.param = null;
        if (options.caller) {
            data.eventType = 'open';
            options.caller.onPopupHandler && options.caller.onPopupHandler(data);
        }
        $.post('/api/BookBorrow/Search',data.param, function (result) {
            var records = result.data;
            if (records.length == 0) {
                alert("BookBorrow Not found !");
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
                    //page list ấy
                    url: '/Admin/BookBorrow/ListBookBorrow',
                    width: data.width || (options.width || 600),
                    height: data.height || (options.height || 'auto'),
                    resizable: true

                }, data.param);
            }
        },'json');

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