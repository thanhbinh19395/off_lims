var framework = window.framework || {};
$.extend(framework, {
    createNamespace: function (baseName, scope) {
        var ns = baseName.split('.');
        var o = scope || window;
        for (var i = 0, l = ns.length; i < l; i++) {
            o = o[ns[i]] = o[ns[i]] || {};
        }
        return o;
    },
    lazyLoad: function (url, callback) {
        var url = url || url.url;
        var callback = callback || url.callback;
        $.ajax({
            url: url,
            dataType: 'script',
            success: callback,
            async: true
        });
    },
    factory: function (id, options) {
        require(['layout', 'panel'], function () {
            //gán ParentId và ViewBag
            options.parentId = ViewBag.ParentId || null;
            options.ViewBag = ViewBag;
            delete ViewBag;
            var pageOptions = {
                dataIn: options
            };
            //vẽ page
            var layoutSetting = layout.setting.page(id, options);
            var page = $('#page').panel(layoutSetting);
            
            //thêm dataOut
            $.extend(pageOptions, { dataOut: $(page).data("widget-panel").options });

            //register page
            framework.global.registerPage(pageOptions.dataOut._pageId, pageOptions);

            //đổi id của thẻ page
            page.attr('id', pageOptions.dataOut._pageId);

            //child function
            if (pageOptions.dataIn.parentId) {
                $.extend(options, {
                    sendMessage: function (message) {
                        var parentPage = framework.global.findPage(pageOptions.dataIn.parentId);
                        parentPage.onMessageReceive && parentPage.onMessageReceive(options, message)
                        $.each(w2ui, function (key, form) {
                            if (form._type == 'form') {
                                if (key.startsWith(pageOptions.dataIn.parentId)) {
                                    $.each(form.fields, function (fieldKey, field) {
                                        if (field.type.startsWith('popup')) {
                                            var data = $(field.el).data('w2field');
                                            data.onMessageReceive && data.onMessageReceive(options, message);
                                        }
                                    });
                                }
                            }
                        });
                    },
                });
            }

            //default function
            if (options.onMessageReceive) {
                pageOptions.onMessageReceive = function (sender, message) {
                    options.onMessageReceive(sender, message);
                }
            }

            $.extend(pageOptions.dataIn, {
                openPopup: function (options, params) {
                    framework.common.openPopup(pageOptions.dataOut._pageId, options, params);
                },
                findElement: function (name, findAll) {
                    return framework.global.findElementByPageId(pageOptions.dataOut._pageId, name, findAll);
                },
                $el: layoutSetting.template,
                pageId: pageOptions.dataOut._pageId,
                pageName:id,
                rootPageId: framework.global.getRootPageId(),
                $pageEl: layoutSetting.template,
                //$contentEl: layoutSetting.items[0].template,
                //$headerEl: layoutSetting.items[1].template,
                //$footerEl: layoutSetting.items[2].template,
                //getPage: function (pageId) {
                //    return {
                //        getElement: function (name) {
                //            console.log(pageId);
                //            framework.global.findWidgetByPageId(pageId, name);
                //        }
                //    };
                //}
            });

            //thêm data vào el sau khi vẽ xong 
            page.data('pageOptions', pageOptions);
            //trigger pageLoadComplete để sử dụng popup
            if (pageOptions.dataIn.parentId)
                $(page.parent()).trigger('pageLoadComplete');
                        
            if (options.onLoadComplete) {
                require(['domReady'], function () {
                    framework.common.tryFunc(options.onLoadComplete.bind(options));
                });
            }
           
        });
    },
});