package com.movie.movie.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MovieInfo implements Serializable {
	@Column(columnDefinition = "LONGTEXT")
	private String info;
	
	public MovieInfo() {}
	
	public MovieInfo(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}
}
