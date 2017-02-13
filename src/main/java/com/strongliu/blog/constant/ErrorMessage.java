package com.strongliu.blog.constant;

/**
 * Created by liuyuzhe on 2017/2/13.
 */
public enum ErrorMessage {
    SUCCESS(0, "Success"),
    FAILED(1, "Failed"),
    EXCEPTION(2, "Exception");

    private int code;
    private String message;

    private ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
