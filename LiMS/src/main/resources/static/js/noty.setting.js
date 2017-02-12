$.noty.defaults = {
  layout: 'topRight',
  theme: 'metroui', // or relax
  type: 'error', // success, error, warning, information, notification
  text: '', // can be HTML or STRING

  dismissQueue: true, // If you want to use queue feature set this true
  force: false, // adds notification to the beginning of queue when set to true
  maxVisible: 5, // you can set max visible notification count for dismissQueue true option,

  template: '<div class="noty_message"><span class="noty_text"></span><div class="noty_close"></div></div>',

  timeout: 1900, // delay for closing event in milliseconds. Set false for sticky notifications
  animation: {
    open: {height: 'toggle'}, // or Animate.css class names like: 'animated bounceInLeft'
    close: {height: 'toggle'}, // or Animate.css class names like: 'animated bounceOutLeft'
    easing: 'swing',
    speed: 500 // opening & closing animation speed
  },
  closeWith: ['click'], // ['click', 'button', 'hover', 'backdrop'] // backdrop click will close all notifications

  modal: false, // if true adds an overlay
  killer: false, // if true closes all notifications and shows itself

  callback: {
    onShow: function() {},
    afterShow: function() {},
    onClose: function() {},
    afterClose: function() {},
    onCloseClick: function() {},
  },

  buttons: false // an array of buttons, for creating confirmation dialogs.
};

window.alert = function (alertMessage) {
    noty({
        text: '<h3><i class="fa fa-exclamation-circle"><\/i> Lỗi<\/h3>' + alertMessage
    });
}
window.alertSuccess = function (alertMessage) {
    noty({
        message: 'Thông báo',
        text: '<h3><i class="fa fa-exclamation-circle"><\/i> Thông báo<\/h3>' + alertMessage,
        type: 'information',
    });
}