$.widget('widget.texteditor', $.widget.base, {
	options: {
		language: 'vi',
		
	},
	_create: function () {
	    //this.options.skin = this.options.skin || 'moono-lisa';
	    this.options.template.attr('id', this.options.name);
		this.options.editor = CKEDITOR.replace(this.options.name, this.options);
		var editor = this.options.editor;
		editor.setData(this.options.data);
		if (this.options.buttons) {
			$.each(this.options.buttons, function (key, value) {
				editor.addCommand(value.id, {
					exec: function (edt) {
						value.onclick(edt);
					}
				});
				editor.ui.addButton(value.id, {
                    title:'hello',
					label: value.text,
					command: value.id,
					toolbar: 'insert',
					icon: value.icon,
				});
			});
		}
		this.options.editor.addImage = function (url) {
		    var element = CKEDITOR.dom.element.createFromHtml('<img src="' + url + '"/>');
		    console.log(element);
		    editor.insertElement(element);
		};
		this._super();
		this._saveData(this.options.editor);
	},
});
var addImage = function (url) {
   
};