package com.strongliu.blog.dto;

import org.springframework.stereotype.Component;

/**
 * Created by liuyuzhe on 2017/2/13.
 */

@Component
public class ResponseListDto {
    private int code;
    private String message;
    private int totalPage;
    private Object data;

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

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
