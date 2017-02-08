$(document).ajaxStart(function () {
	var size = '48px'
	$.blockUI({
		overlayCSS: {
			backgroundColor: '#696969'
		},
		css: {
			'border': 'none',
			'width': size,
			'height': size,
			'top': '50%',
			'left': '50%',
			'margin-top': '-24px',
			'margin-left': '-24px',
			'background-color': 'transparent',
		},
		message: '<img src="/Content/Images/loader.gif" style="width: ' + size + '; height: ' + size + ';" />'
	});
})
        .ajaxStop($.unblockUI)
        .ajaxError($.unblockUI);
$(window).error($.unblockUI);