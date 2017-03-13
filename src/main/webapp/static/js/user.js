/**
 * Created by liuyuzhe on 2017/3/4.
 */

//document.write("<script src='../../../static/js/regular.js' charset='utf-8' ></script>");

$("#register").click(function() {
    //var username = $("#registerForm input[name='username']").val();
    var username = $(this).parents("form").find("input[name='username']").val();
    $("#alertUsername").toggle(!isUsername(username));

    //$.post("/user/register", $("#registerForm").serialize(), function(response, status) {
    //    if (response.code == 0) {
    //        window.location.href = '/user/login';
    //    } else {
    //        alert(response.message);
    //    }
    //});
});

$("#login").click(function() {
    $.post("/user/login", $("#loginForm").serialize(), function(response, status) {
       if (response.code == 0) {
           window.location.href = "/user/";
       } else {
           alert(response.message);
       }
    });
});



