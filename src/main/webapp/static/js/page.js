/**
 * Created by liuyuzhe on 2017/4/20.
 */

(function(selector){
    $(selector).hide();
    $(window).scroll(function() {
        if ($(this).scrollTop() > 110) {
            $(selector).fadeIn(500);
        }
        else {
            $(selector).fadeOut(500);
        }
    });
    $(selector).click(function() {
        $("body, html").animate(
            {scrollTop : 0},
            500
        );
    });
})("#back-to-top");

