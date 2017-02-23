/**
 * Created by Hung Thinh on 18/02/2017.
 */

$(document).ready(function () {
    $('#update_pass_form').validate({
        rules : {
            newPassword: {
                minlength : 6,
                maxlength : 30
            },
            new_password_confirm: {
                equalTo: "#newPassword"
            }
        },
        highlight: function (element) {
            $(element).removeClass('validate valid');
            $(element).addClass('validate invalid');
        },
        unhighlight: function (element) {
            $(element).removeClass('validate invalid');
            $(element).addClass('validate valid');

        },
        errorElement: 'span',
        onfocusout: function (element, event) {
            this.element(element);
        }
    });
});
