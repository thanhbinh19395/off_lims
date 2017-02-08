require.config({
    baseUrl: '/js',
    paths: {
        framework: 'framework',
        common: 'framework.common',
        global: 'framework.global',
        inputpage: 'framework.layout.inputpage',
        listpage: 'framework.layout.listpage',
        widget: 'widget.setting',
        layout: 'layout.setting',
        w2uiSetting:'w2ui.setting',
        domReady: '../plugins/requirejs/domReady',
        // widgets
        base: 'widget/widget.base',
        button: 'widget/widget.button',
        form: 'widget/widget.form',
        pagination: 'widget/widget.pagination',
        panel: 'widget/widget.panel',
        grid: 'widget/widget.grid',
        texteditor: 'widget/widget.texteditor',
        gallery: 'widget/widget.gallery',
        uploader: 'widget/widget.uploader',
        tabs: 'widget/widget.tabs',
        field: 'widget/widget.field',
        boxcontent: 'widget/widget.boxcontent',
        //plugin
        ckeditor: '../plugins/ckeditor/ckeditor',
        pluploadcore: '../plugins/plupload-2.1.9/js/plupload.full.min',
        pluploadui: '../plugins/plupload-2.1.9/js/jquery.ui.plupload/jquery.ui.plupload',
        jquery: '../flaty/assets/jquery/jquery-2.1.1.min',
        bootstrap: '../plugins/bootstrap-3.3.7/js/bootstrap',
        jquerySlimscroll: '../flaty/assets/jquery-slimscroll/jquery.slimscroll.min',
        jqueryCookie: '../flaty/assets/jquery-cookie/jquery.cookie',
        flot: '../flaty/assets/flot/jquery.flot',
        flotResize: '../flaty/assets/flot/jquery.flot.resize',
        flotPie: '../flaty/assets/flot/jquery.flot.pie',
        flotStack: '../flaty/assets/flot/jquery.flot.stack',
        flotCrosshair: '../flaty/assets/flot/jquery.flot.crosshair',
        flotTooltip: '../flaty/assets/flot/jquery.flot.tooltip.min',
        sparkline: '../flaty/assets/sparkline/jquery.sparkline.min',
        flatyScript: '../flaty/js/flaty',
        w2ui: '../plugins/w2ui/w2ui-1.5.rc1',
        twbspagination: '../plugins/twbspagination/jquery.twbsPagination',
        jqueryUI: '../plugins/jquery-ui-1.11.4/jquery-ui',
        jqueryUIDiaglogExtend: '../plugins/jquery-ui-DialogExtend/jquery.dialogextend',
        noty: '../plugins/noty-2.3.11/js/noty/packaged/jquery.noty.packaged.min',
        notySetting: 'noty.setting',
        blockUI: '../plugins/blockUI/jquery.blockUI',
        blockUISetting: 'blockUI.setting'
    },
    shim: {
        //jquery deps
        jqueryUI: ['jquery'],
        jqueryUIDiaglogExtend: ['jquery','jqueryUI'],
        blockUI:['jquery'],
        pluploadcore: ['jquery'],
        bootstrap: ['jquery'],
        pluploadui: ['pluploadcore', 'jqueryUI'],
        jquerySlimscroll: ['jquery'],
        flot: ['jquery'],
        sparkline: ['jquery'],
        flatyScript: ['jquery', 'jquerySlimscroll', 'bootstrap'],
        w2ui: ['jquery'],
        w2uiSetting:['w2ui'],

        //flot
        flotResize: ['flot'],
        flotPie: ['flot'],
        flotStack: ['flot'],
        flotCrosshair: ['flot'],
        flotTooltip: ['flot'],
        noty: ['jquery'],
        notySetting: ['noty'],
        //framework
        framework: ['widget', 'global', 'common','jqueryUI'],
        common:['jqueryUIDiaglogExtend'],
        // layouts
        inputpage: ['framework', 'widget'],
        listpage: ['framework', 'widget', 'panel'],
        layout: ['global', 'widget'],

        // widget
        base: ['w2ui', 'jqueryUI', 'w2uiSetting'],
        button: ['base'],
        form: ['base', 'field'],
        pagination: ['base', 'twbspagination'],
        panel: ['base'],
        grid: ['base', 'pagination'],
        texteditor: ['base', 'ckeditor'],
        gallery: ['base'],
        uploader: ['base', 'pluploadui'],
        tabs: ['base'],
        boxcontent: ['base'],

        // layouts
        inputpage: ['framework', 'widget'],
        listpage: ['framework', 'widget', 'panel'],

        layout: ['global', 'widget'],

        //plugins
        blockUISetting:['blockUI']
    }
});