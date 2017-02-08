/* This widget contains only fields */
$.widget('widget.form', $.widget.base, {
    template: [
        "<table>",
            "{0}",
        "</table>"
    ].join(),
    options: {
        name: null,
        fieldPerRow: 2,
        fields: [],
        actions: [],
        boderSpacing: 0,
        labelWidth: 150
    },
    _create: function () {
        this.element.empty();
        this.element.append(this._createFormElement());
        var form = this.element.w2form(this.options);

        $.extend(form, {
            findElement: function (element) {
                var el = $(form.$el).find(element);
                if (el.length > 0) {
                    return el;
                }
                if (element && element[0] && element[0].match(/[a-zA-Z]/g)) {
                    element = '#' + element;
                }
                return $(form.$el).find(element);
            }
        });

        this._super();
        this._saveData(form);

    },
    _createFormElement: function () {
        var table = $('<table>')
                        .addClass('table-form') // this class needs to be added in order to styling correctly
                        .css('border-spacing', this.options.boderSpacing)
                        .css('width', '100%')
                        .css('margin', '0px 0px')
        ;
                            
        var tr = $('<tr>');//.css('border', '1px solid');

        for (var i = 0, v = 0, field; (field = this.options.fields[i]) ; i++) {
            var fieldElement = this._createInputElement(field);
            var td = $('<td>').append(fieldElement).attr('colspan', field.span);

            if (!fieldElement) {
                if (field.span && field.span >= this.options.fieldPerRow) {
                    // add a seperator if the row is null
                    td.append($('<hr>').addClass('separator'));
                }
            }

            var span = field.span || 1;

            if (span > this.options.fieldPerRow) {
                throw new Error("span > fieldPerRow");
            }
            var nextV = v + span;

            if (nextV >= this.options.fieldPerRow) {
                if (nextV > this.options.fieldPerRow) {
                    table.append(tr);
                    i--;
                } else {
                    table.append(tr.append(td));
                }

                tr = $('<tr>');
                v = 0;
                continue;
            } else {
                tr.append(td);
            }

            if (i + 1 == this.options.fields.length) {
                table.append(tr.append(td));
                break;
            }

            v += span;
        }

        return $('<div>')
            .append(table)
			.css({
			    'width': '100%',
			    'padding': '5px'
			});
    },
    _createInputElement: function (fieldOption) {
        if (fieldOption.type == 'empty') {
            return null;
        }
        var inputDiv = $('<div>').addClass('w2ui-field');
        var label = $('<label>').css({ 'width': (this.options.labelWidth + 'px'), 'text-align': 'left', 'margin-left': '2px' });
        var input = this._getInputByOption(fieldOption).attr('name', fieldOption.field);
        if (fieldOption.html){
            label.text(fieldOption.html.caption || (fieldOption.caption || fieldOption.field));
            if(fieldOption.html.attr)
                input.attr(fieldOption.html.attr);
        }
        else {
            label.text(fieldOption.caption || fieldOption.field);
        }
        
        var inputBox = $('<div>').append(input).css('margin-left', (this.options.labelWidth + 1) + 'px');

        input.css({
            border: 0
        });

        switch (fieldOption.type) {
            case 'list':
            case 'select':
                inputBox.css({
                    padding: '1px'
                });
                break;
            case 'enum':
                inputBox.css({
                    border: '0',
                    backgroundColor: 'transparent',
                    padding: 0
                });
                input.css({ display: 'none' });
                break;
            default:
                break;
        }

        return inputDiv.append(label).append(inputBox);
    },
    _getInputByOption: function (fieldOption) {
        var self = this;
        var tmp = null;
        switch (fieldOption.type) {
            case 'textarea':
                tmp = $('<textarea>').css('resize', 'vertical');
                break;
            case 'select':
                tmp = $('<select>');
                break;
            default:
                tmp = $('<input>').attr('type', 'text');
                break;
        }
        if (tmp) {
            tmp.css('width', '100%');
        }
        return tmp;
    }
});