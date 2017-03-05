/**
 * Created by liuyuzhe on 2017/3/4.
 */

function registerForm() {
    $.post(
        "admin/login", $("registerForm").serialize(), function(response) {
            if (response && response.success) {
                window.location.href = "/admin/index"
            }
            else {
                alert("注册失败")
            }
        }
    );
}

function loginForm() {

}