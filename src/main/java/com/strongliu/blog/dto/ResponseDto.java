package com.strongliu.blog.dto;

import com.strongliu.blog.constant.ErrorCode;

/**
 * Created by liuyuzhe on 2017/2/13.
 */

public class ResponseDto<T> {
    private int code;
    private String message;
    private T data;

    public ResponseDto(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ResponseDto(ErrorCode errorCode, T data) {
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

    public void setData(T data) {
        this.data = data;
    }
}
