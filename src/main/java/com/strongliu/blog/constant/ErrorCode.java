package com.strongliu.blog.constant;

public enum ErrorCode {
	SUCCESS(0, "Success"),
	err_param_miss(10001, "Param Miss");
	
	private int code;
    private String message;

    private ErrorCode(int code, String message) {
    	this.code = code;
    	this.message = message;
    }

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
    
	public static ErrorCode getErrorCodeByCode(int code) {
		ErrorCode[] all = ErrorCode.values();
		for (ErrorCode errorCode : all) {
			if (errorCode.getCode() == code) {
				return errorCode;
			}
		}
		
		return null;
	}
    
}
