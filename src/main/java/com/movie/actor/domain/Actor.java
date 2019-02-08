package com.movie.actor.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.movie.movie.domain.MovieActor;

@Entity
public class Actor {
	@Id
	@Column(name = "actor_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Embedded
	private ActorName actorName;
	
	@Embedded
	private ActorInfo actorInfo;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "movieActorId.actor", orphanRemoval = true)
	private List<MovieActor> movieActors = new ArrayList<>();
	
	public Actor() {}
	
	public Actor(ActorName actorName, ActorInfo actorInfo) {
		this.setActorName(actorName);
		this.setActorInfo(actorInfo);
	}
	
	public Actor(ActorName actorName, ActorInfo actorInfo, List<MovieActor> movieActors) {
		this(actorName, actorInfo);
		this.setMovieActors(movieActors);
	}

	public ActorName getActorName() {
		return actorName;
	}
	
	public void setActorName(ActorName actorName) {
		if (actorName == null || actorName.getName() == null || actorName.getName().isEmpty()) {
			throw new IllegalArgumentException("actorName");
		}
		this.actorName = actorName;
	}

	public ActorInfo getActorInfo() {
		return actorInfo;
	}
	
	public void setActorInfo(ActorInfo actorInfo) {
		if (actorInfo == null || actorInfo.getInfo() == null || actorInfo.getInfo().isEmpty()) {
			throw new IllegalArgumentException("actorInfo");
		}
		this.actorInfo = actorInfo;
	}

	public List<MovieActor> getMovieActors() {
		return movieActors;
	}

	private void setMovieActors(List<MovieActor> movieActorIds) {
		this.movieActors.clear();
		this.movieActors.addAll(movieActors);
	}
	
	private void addMovieActor(MovieActor movieActor) {
		this.movieActors.add(movieActor);
	}
}
