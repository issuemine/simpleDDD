package com.movie.movie.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ActorRole implements Serializable {
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
