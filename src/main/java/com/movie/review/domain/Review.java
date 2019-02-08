package com.movie.review.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.movie.movie.domain.Movie;

@Entity
public class Review {
	@Id
	@Column(name = "review_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private Content content;
	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	public Review() {}
	
	public Review(Movie movie, Content content) {
		this.setContent(content);
		this.setMovie(movie);
	}

	public Long getId() {
		return id;
	}

	public Content getContent() {
		return content;
	}

	private void setContent(Content content) {
		if (content == null || content.getContent() == null || content.getContent().isEmpty() ||
				content.getContent() == null || content.getWriter().isEmpty()) {
			throw new IllegalArgumentException("content");
		}
		this.content = content;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	private void setMovie(Movie movie) {
		if (movie == null) {
			throw new IllegalArgumentException("movie");
		}
		this.movie = movie;
	}
}
