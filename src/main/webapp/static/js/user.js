/**
 * Created by liuyuzhe on 2017/3/4.
 */

//document.write("<script src='../../../static/js/regular.js' charset='utf-8' ></script>");

$("#register").click(function() {
    var username = $("#registerForm input[name='username']").val();
    if (!isUsername(username)) {
        $("#alertUsername").show();
        return;
    } else {
        $("#alertUsername").hide();
    }

    var email = $("#registerForm input[name='email']").val();
    if (!isEmail(email)) {
        $("#alertEmail").show();
        return;
    } else {
        $("#alertEmail").hide();
    }

    var password = $("#registerForm input[name='password']").val();
    if (!isPassword(password)) {
        $("#alertPassword").show();
        return;
    } else {
        $("#alertPassword").hide();
    }

    var repeat = $("#repeatPassword").val();
    if (repeat != password) {
        $("#alertRepeatPassword").show();
        return;
    } else {
        $("#alertRepeatPassword").hide();
    }

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



