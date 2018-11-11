
$(function(){
    $('#AddToCartButton').click(function(e) {
        alert("I'm in javascript");
        //todo: remove unused code - especially on UI!
        //Prevent default submission of form
        // e.preventDefault();
        //Remove all errors
        // $('input').next().remove();

        $.post({
            url : '/add-to-cart/',
            data : $('form[name=addToCartForm]').serialize(),
            success : function() {
            }
        });
    });
});