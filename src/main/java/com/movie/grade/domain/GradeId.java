package com.movie.grade.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.movie.movie.domain.Movie;

@Embeddable
public class GradeId implements Serializable {
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	private String reviewer;
	
	public GradeId() {}
	
	public GradeId(Movie movie, String reviewer) {
		this.movie = movie;
		this.reviewer = reviewer;
	}	
	
	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
}
