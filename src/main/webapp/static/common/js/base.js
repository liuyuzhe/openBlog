/**
 * Created by liuyuzhe on 2017/5/6.
 */

var Base = {

    init : function() {
        var base = {};

        base.isEmpty = function(obj) {
            return (obj == null || obj == undefined || obj == "" || obj.length == 0);
        };

        base.isUsername = function(text) {
            if (this.isEmpty(text)) {
                return false;
            }

            var pattern = /^[A-Za-z0-9_-]{6,18}$/;
            return pattern.exec(text);
        };

        base.isPassword = function(text) {
            if (this.isEmpty(text)) {
                return false;
            }

            var pattern = /^[A-Za-z0-9_-]{6,18}$/;
            return pattern.exec(text);
        };

        base.isNickname = function(text) {
            if (this.isEmpty(text)) {
                return false;
            }

            var pattern = /^[A-Za-z0-9_-\u4E00-\u9FA5]{2,16}$/;
            return pattern.exec(text);
        };

        base.isEmail = function(text) {
            if (this.isEmpty(text)) {
                return false;
            }

            var pattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\.[a-zA-Z0-9_-]+$/;
            return pattern.exec(text);
        };

        base.isMobilephone = function(text) {
            if (this.isEmpty(text)) {
                return false;
            }

            var pattern = /^1[3|4|5|7|8][0-9]{9}$/;
            return pattern.exec(text);
        };

        base.isTelephone = function(text) {
            if (this.isEmpty(text)) {
                return false;
            }

            var pattern = /^\d{3}-\d{8}|\d{4}-\d{7,8}$/;
            return pattern.exec(text);
        };

        base.isUrl = function(text) {
            if (this.isEmpty(text)) {
                return false;
            }

            var pattern = /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/;
            return pattern.exec(text);
        };

        return base;
    }
};