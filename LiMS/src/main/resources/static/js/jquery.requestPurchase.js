/**
 * Created by Hung Thinh on 19/02/2017.
 */



$(document).ready(function () {
    $('#request_purchase_form').validate({
        rules:{
        publication_year: {
                range : [1600,2017]
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
   $('#btnClear').click(function() {
        $('#title').val('');
        $('#author').val('');
        $('#publisher').val('');
        $('#publication_year').val('0');
        $('#additionalInformation').val('');
    });
});

