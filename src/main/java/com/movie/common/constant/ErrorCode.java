package com.movie.common.constant;

public enum ErrorCode {
	EXIST_MEMBER_ERROR(1001),
	VALIDATION_LOGIN_ERROR(1002),
	
	NOT_EXIST_MOVIE_ERROR(2001);
	
	private Integer code;
	
	private ErrorCode(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
}
