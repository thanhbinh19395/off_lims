$.widget('widget.tabs', $.widget.base, {
    options: {
    },
    _create: function () {
        var tabs = this.element.w2tabs(this.options);
       
        this._super();
        this._saveData(tabs);
    },
  
});