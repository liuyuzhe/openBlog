package com.strongliu.blog.dto;

import com.strongliu.blog.constant.ErrorCode;

/**
 * Created by liuyuzhe on 2017/2/13.
 */

public class ResponseDto {
    private int code;
    private String message;
    private Object data;

    public ResponseDto(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ResponseDto(ErrorCode errorCode, Object data) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
