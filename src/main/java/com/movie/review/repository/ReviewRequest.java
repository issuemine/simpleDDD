package com.movie.review.repository;

public class ReviewRequest {
	private Long movieId;
	
	private String content;

	public ReviewRequest(Long movieId, String content) {
		this.movieId = movieId;
		this.content = content;
	}
	
	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
