var framework = window.framework || {};
framework.common = framework.common || {};
$.extend(framework.common, {
    api: function (url, param, calback) {
        var url = url.url || url;
        var param = url.param || param;
        var calback = url.calback || calback;

    },
    openWindow: function (params) {

    },
    openPopup: function (parentId, options, params) {
        var defaultOptions = {
            name: 'unnamedPopup',
            minHeight: 0,
            height: 450,
            width: 950,
            modal: false,
            resizable: true,
            position: { my: "center top+150px", at: "center top", of: window },
            openMultiple: false,
            hide: 'fade',
            show: 'fade',
            close: function (event, ui) {
                //remove ra khỏi framework.global.findElementByPageId
                if (event.currentTarget) {
                    framework.global.unregisterElementByPageId(parentId, options.name || 'unnamedPopup');
                }
                //remove popup html
                $(this).dialog('destroy').dialogExtend('destroy').remove();
            }
        };

        var defaulDialogExtendOptions = {
            
            maximizable: true,
            minimizable: true,
            collapsable: false,
            maximize: function (e) {
                $(e.target).trigger('resize');
            },
            restore: function (e) {
                $(e.target).trigger('resize');
            },
            minimize: function (e) {
                $(e.target).trigger('resize');
            }
        }


        $.extend(defaultOptions, options);
        $.extend(defaulDialogExtendOptions, options);

        var params = params || {};
        $.extend(params, { ParentId: parentId });
        var openPopup = function () {
            var dialog = $('<div>').load(options.url, params);
            $(dialog).attr('style', $(dialog).attr('style') + '; ' + 'overflow: auto !important');
            $(dialog).dialog(defaultOptions).dialogExtend(defaulDialogExtendOptions);
            framework.global.registerElementByPageId(parentId, defaultOptions.name, dialog);
            $(dialog).on('*', function (e) { console.log(e); });
            //sau khi vẽ page xong
            $(dialog).on('pageLoadComplete', function (event) {
                //tìm con chứa data là pageOptions
                $.each(dialog[0].childNodes, function (k, v) {
                    var data = $(v).data();
                    if (data.pageOptions) {
                        $.extend(data.pageOptions.dataIn, {
                            close: function () {
                                framework.global.unregisterElementByPageId(parentId, options.name || 'unnamedPopup');
                                $(dialog).dialog('close');
                            },
                            minimize: function () {
                                $(dialog).dialogExtend('minimize');
                            },
                            restore: function () {
                                $(dialog).dialogExtend('restore');
                            },
                            maximize: function () {
                                $(dialog).dialogExtend('maximize');
                            },
                            collapse: function () {
                                $(dialog).dialogExtend('collapse');
                            },
                            getState: function () {
                                if (!$(dialog).dialog("isOpen"))
                                    return 'closed';
                                else
                                    return $(dialog).dialogExtend('state');
                            },
                            moveToTop: function () {
                                $(dialog).dialog("moveToTop");
                            },
                            loadContent: function (url, params) {
                                params = params || {};
                                //parent id của page mới sẽ là pageid của page cũ
                                $.extend(params, { ParentId: data.pageOptions.dataIn.pageId });
                                $(dialog).load(url, params);
                            }
                        });
                    }
                });
            });
        };
        if (defaultOptions.openMultiple)
            openPopup();
        else {
            var popup = framework.global.findElementByPageId(parentId, defaultOptions.name);
            if (popup) {
                try {
                    $(popup).dialog('close');
                    framework.global.unregisterElementByPageId(parentId, options.name || 'unnamedPopup');
                }
                catch (err) {
                    console.log(err);
                }
            }
            openPopup();

        }



    },
    tryFunc: function (callback, timeout, maxCount, count) {
        var self = this;
        try {
            count = count || 0;
            console.log(count++);
            callback && callback();
        }
        catch (err) {
            if (count >= (maxCount || 10)) {
                throw err;
            }
            else {
                setTimeout(function () {
                    self.tryFunc(callback, timeout, maxCount, count);
                }, timeout || 10);
            }
        }
    },
    cmdResultNoti: function (data, options) {
        if (data.IsSuccess)
        {
            this.noti(data.Message || 'Thực hiện thành công', options);
        }
        else {
            this.noti(data.Message || 'Lỗi không xác định', { title: 'Lỗi' });
        }
    },
    noti: function (message,options) {
        var opt = {
            text: message,
            sticky: false,
            title: "Thông báo",
            time: 5000
        };

        if (options) {
            $.extend(true, opt, options);
        }

        $.gritter.add(opt);
    }
});