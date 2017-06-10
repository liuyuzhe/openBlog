package com.strongliu.blog.constant;

public enum ErrorCode {

	SUCCESS(0, "Success"),

	// 客户端通用错误
	ERROR_BAD_URL(-1001, "Bad url"),
	ERROR_PARAM_MISS(-1002, "Parameter miss"),
	ERROR_PARAM_INVALID(-1003, "Invalid parameter"),
	ERROR_INVALID_SIGNATURE(-1004, "Authorize failed"),
	ERROR_ENCRYPT_FAILED(-1005, "Encrypt failed"),
	ERROR_SESSION_EXPIRES(-1006, "Session expires"),
	ERROR_REQUEST_TOO_MANY_TIMES(-1007, "Request too many times"),
	ERROR_RESOURCE_NOT_FOUND(-1008, "Resource not found"),

	// 客户端接入服务错误
	ERROR_EXISTED_USER(-1101, "Existed user"),
	ERROR_SAVE_REGISTER_FAILED(-1103, "Save register failed"),
	ERROR_NO_EXISTED_USER(-1104, "No existed user"),
	ERROR_PASSWORD_NOT_MATCH(-1105, "Password not match"),
	ERROR_REPEAT_PASSWORD_NOT_MATCH(-1106, "Repeat Password not match"),

	// 服务器通用错误
	ERROR_SERVER_TIMEOUT(-2001, "Server deal timeout"),
	ERROR_IO_ACCESS_FAILED(-2002, "IO access failed"),
	ERROR_DB_FAILED(-2003, "Database deal failed"),
	ERROR_CONNECTION_BROKEN(-2004, "Connection broken"),
	ERROR_SERVER_INTERNAL(-2005, "Server internal error");

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
