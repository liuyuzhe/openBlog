/**
 * Created by liuyuzhe on 2017/3/13.
 */

/*
    /.../  定义正则表达式
    /.../g 全局匹配
    /.../i 不区分大小写
    /.../m 多行匹配
*/

function isEmpty(text) {
    if (text.length != 0) {
        return false;
    }
    return true;
}

function isUsername(text) {
    var pattern = /^[A-Za-z0-9_-]{6,18}$/;
    if (!pattern.exec(text)) {
        return false;
    }
    return true;
}

function isPassword(text) {
    var pattern = /^[A-Za-z0-9_-]{6,18}$/;
    if (!pattern.exec(text)) {
        return false;
    }
    return true;
}

function isNickname(text) {
    var pattern = /^[A-Za-z0-9_-\u4E00-\u9FA5]{2,16}$/;
    if (!pattern.exec(text)) {
        return false;
    }
    return true;
}

function isEmail(text) {
    var pattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z0-9_-]+$/;
    if (!pattern.exec(text)) {
        return false;
    }
    return true;
}

function isMobilephone(text) {
    var pattern = /^1[3|4|5|7|8][0-9]{9}$/;
    if (!pattern.exec(text)) {
        return false;
    }
    return true;
}

function isTelephone(text) {
    var pattern = /^\d{3}-\d{8}|\d{4}-\d{7,8}$/;
    if (!pattern.exec(text)) {
        return false;
    }
    return true;
}

function isUrl(text) {
    var pattern = /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/;
    if (!pattern.exec(text)) {
        return false;
    }
    return true;
}