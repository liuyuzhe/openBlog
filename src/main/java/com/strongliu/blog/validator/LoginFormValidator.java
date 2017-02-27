package com.strongliu.blog.validator;

import com.strongliu.blog.utility.RegularUtil;
import com.strongliu.blog.vo.LoginFormVo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by liuyuzhe on 2017/2/27.
 */

@Component
public class LoginFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginFormVo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", null, "用户名不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null, "密码不能为空");

        LoginFormVo loginInfo = (LoginFormVo) target;
        if (!loginInfo.getUsername().matches(RegularUtil.USER_NAME)) {
            errors.reject("username", null, "用户名为6到15位");
        }
        if (!loginInfo.getPassword().matches(RegularUtil.PASSWORD)) {
            errors.reject("password", null, "密码为6到15位");
        }
    }
}
