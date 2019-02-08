package com.movie.member.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class MemberId implements Serializable {
	@Column(name = "id")
	private String email;
	
	public MemberId() {}
	
	public MemberId(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
}
