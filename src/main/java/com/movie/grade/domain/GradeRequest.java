package com.movie.grade.domain;

public class GradeRequest {
	Long movieId;
	
	Double grade;
	
	public GradeRequest(Long movieId, Double grade) {
		this.movieId = movieId;
		this.grade = grade;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}
}
