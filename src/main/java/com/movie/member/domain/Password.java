package com.movie.member.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Password implements Serializable {
	private String password;
	
	public Password() {}
	
	public Password(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}
