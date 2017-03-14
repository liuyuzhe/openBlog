/**
 * Created by liuyuzhe on 2017/3/4.
 */

var checkUsername = function (username) {
    if (!isUsername(username)) {
        $("#alertUsername").show();
        return false;
    } else {
        $("#alertUsername").hide();
        return true;
    }
}

var checkEmail = function (email) {
    if (!isEmail(email)) {
        $("#alertEmail").show();
        return false;
    } else {
        $("#alertEmail").hide();
        return true;
    }
}

var checkPassword = function (password) {
    if (!isPassword(password)) {
        $("#alertPassword").show();
        return false;
    } else {
        $("#alertPassword").hide();
        return true;
    }
}

var checkRepeatPassword = function (password, repeat) {
    if (repeat !== password) {
        $("#alertRepeatPassword").show();
        return false;
    } else {
        $("#alertRepeatPassword").hide();
        return true;
    }
}

$("#registerForm input[name='username']").blur(function() {
    checkUsername($(this).val());
});

$("#registerForm input[name='email']").blur(function() {
    checkEmail($(this).val());
});

$("#registerForm input[name='password']").blur(function() {
    checkPassword($(this).val());
});

$("#repeatPassword").blur(function() {
    var password = $("#registerForm input[name='password']").val();
    var repeat = $(this).val();
    checkRepeatPassword(password, repeat);
});

$("#register").click(function() {
    var username = $("#registerForm input[name='username']").val();
    var email = $("#registerForm input[name='email']").val();
    var password = $("#registerForm input[name='password']").val();
    var repeatPassword = $("#repeatPassword").val();
    if (!checkUsername(username) || !checkEmail(email) || !checkPassword(password) ||
        !checkRepeatPassword(password, repeatPassword)) {
        return;
    }

    $.post("/user/register", $("#registerForm").serialize(), function(response, status) {
        if (response.code == 0) {
            window.location.href = '/user/login';
        } else {
            alert(response.message);
        }
    });
});

$("#loginForm input[name='username']").blur(function() {
    checkUsername($(this).val());
});

$("#loginForm input[name='password']").blur(function() {
    checkPassword($(this).val());
})

$("#login").click(function() {
    var username = $("#loginForm input[name='username']").val();
    var password = $("#loginForm input[name='pasword']").val();
    if (!checkUsername(username) || !checkPassword(password)) {
        return;
    }

    $.post("/user/login", $("#loginForm").serialize(), function(response, status) {
       if (response.code == 0) {
           window.location.href = "/user/";
       } else {
           alert(response.message);
       }
    });
});



