package com.strongliu.blog.validator;

import com.strongliu.blog.utility.RegularUtil;
import com.strongliu.blog.vo.RegisterFormVo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by liuyuzhe on 2017/2/27.
 */

@Component
public class RegisterFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterFormVo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", null, "用户名不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null, "密码不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", null, "邮箱不能为空");

        RegisterFormVo registerFormVo = (RegisterFormVo) target;
        if (!registerFormVo.getUsername().matches(RegularUtil.USER_NAME)) {
            errors.rejectValue("username", null, "用户名输入非法");
        }
        if (!registerFormVo.getPassword().matches(RegularUtil.PASSWORD)) {
            errors.rejectValue("password", null, "密码输入非法");
        }
        if (!registerFormVo.getEmail().matches(RegularUtil.EMAIL)) {
            errors.rejectValue("email", null, "邮箱输入非法");
        }
    }
}
