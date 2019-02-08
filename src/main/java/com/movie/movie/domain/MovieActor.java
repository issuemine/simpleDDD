package com.movie.movie.domain;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "movie_actor")
public class MovieActor {
	@EmbeddedId
	private MovieActorId movieActorId;
	
	@Embedded
	private ActorRole actorRole;
	
	public MovieActor() {}
	
	public MovieActor(MovieActorId movieActorId, ActorRole actorRole) {
		this.setMovieActorId(movieActorId);
		this.setActorRole(actorRole);
	}
	
	public MovieActorId getMovieActorId() {
		return movieActorId;
	}

	private void setMovieActorId(MovieActorId movieActorId) {
		if (movieActorId == null) {
			throw new IllegalArgumentException("movieActorId");
		}
		this.movieActorId = movieActorId;
	}

	public ActorRole getActorRole() {
		return actorRole;
	}

	private void setActorRole(ActorRole actorRole) {
		if (actorRole == null) {
			throw new IllegalArgumentException("actorRole");
		}
		this.actorRole = actorRole;
	}
}
