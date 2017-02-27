package com.strongliu.blog.validator;

import com.strongliu.blog.vo.PostFormVo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by liuyuzhe on 2017/2/27.
 */

@Component
public class PostFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PostFormVo.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", null, "请填写文章标题");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", null, "请填写文章内容");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "categories", null, "请选择文章分类");
    }
}
