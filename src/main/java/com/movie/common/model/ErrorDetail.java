package com.movie.common.model;

import com.movie.common.constant.ErrorCode;

public class ErrorDetail {
	private Integer code;
	
	private String message;
	
	public ErrorDetail(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
