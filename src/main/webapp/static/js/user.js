/**
 * Created by liuyuzhe on 2017/3/4.
 */

$("#register").click(function() {
    $.post("/user/register", $("#registerForm").serialize(), function(response, status) {
        if (response.code == 0) {
            window.location.href = '/user/login';
        } else {
            alert(response.message);
        }
    });
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



