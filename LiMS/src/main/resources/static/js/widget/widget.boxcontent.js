$.widget('widget.boxcontent', {
    options: {
        data: {
            title: '',
            content: '',
        },
        divClass: 'col-md-12',
        contentCss: {
            'display': 'block',
            'background-color': '#FFF'
        },
        icon: null,
        color: '#5ab4da',
        buttons: [], //mang de chua cac button
        boxCss: {
            'padding-left': '5px',
            'padding-right': '5px',
            'padding-top':'5px'
        },
    },
    _create: function () {
        var self = this;
        this.element.addClass(this.options.divClass).css(self.options.boxCss);
        //tạo thẻ div box
        self.options.box = $('<div>').addClass('box').appendTo(this.element);


        //tạo thanh title
        this.boxTitle = $('<div>').addClass('box-title')
            .css('padding', '5px')
            .appendTo(self.options.box)
        ;
        var title = $('<h3>').html(self.options.data.title).appendTo(this.boxTitle);
        if (this.options.icon)
            $('<i>').addClass(self.options.icon).prependTo(title);

        var boxToolInTitle = $('<div>').addClass('box-tool').appendTo(this.boxTitle);
        this.boxTool = $('<div>').addClass('box-tool');
        //tạo button
        $.each(self.options.buttons, function (key, button) {
            self.addButton(button);
        });

        //them content
        var boxContent = $('<div>').addClass('box-content')
            .appendTo(self.options.box)
            .css(this.options.contentCss)
        ;
        if (self.options.data.content) {
            //nếu là đối tượng jQuery
            if (self.options.data.content instanceof jQuery)
                boxContent.append(self.options.data.content)
                //nếu là chuỗi html
            else
                boxContent.html(this.options.data.content);
            boxContent.append(this.boxTool);
        }

        if (self.options.color) {
            this.boxTitle.css('background-color', self.options.color);
            boxContent.css('border', '1px solid ' + self.options.color);
        }
        this._createEvent();
    },
    _createEvent: function () {
        var self = this;
        $(this.boxTitle).click(function (e) {
            $(this).parents('.box')
                .children('.box-content')
                .toggle()
            ;
        });
    },
    _destroy: function () {
        this.element.html('');
    },
    addButton: function (button) {
        var aTag = $('<div>');
        if (button.text || button.icon) {
            aTag.addClass('btn')
                .addClass(button.btnClass)
                .html(button.text)
            ;
            if (button.icon)
                aTag.prepend('<i class="' + button.icon + '"></i> ');
        }
        else {
            console.log(button);
            throw 'Error ! Button must have icon or text';
        }
        aTag.click(button.onClick.bind('click', this.options))
            .css(button.css || {})
            .css('background-color', this.options.color);

        aTag.appendTo(this.boxTool);
            
        ;
        this.boxTool.append(' ');
    }
});