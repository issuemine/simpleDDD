package com.movie.member.event;

import org.springframework.context.ApplicationEvent;

public class MovieBoughtEvent extends ApplicationEvent {
	private String email;
	
	public MovieBoughtEvent(Object source, String email) {
		super(source);
		this.email= email;
	}

	public String getEmail() {
		return email;
	}
}
