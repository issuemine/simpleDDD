package com.movie.movie.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.movie.actor.domain.Actor;

@Embeddable
public class MovieActorId implements Serializable {
	@ManyToOne
	@JoinColumn(name = "actor_id")
	private Actor actor;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
