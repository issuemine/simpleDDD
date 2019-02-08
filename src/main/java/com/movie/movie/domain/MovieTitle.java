package com.movie.movie.domain;

import javax.persistence.Embeddable;

@Embeddable
public class MovieTitle {
	String title;

	public MovieTitle() {}
	
	public MovieTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
