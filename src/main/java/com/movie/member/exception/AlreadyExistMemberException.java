package com.movie.member.exception;

public class AlreadyExistMemberException extends RuntimeException {
	private String email;
	
	public AlreadyExistMemberException(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
}
