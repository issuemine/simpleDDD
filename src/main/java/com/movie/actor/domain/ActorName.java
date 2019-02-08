package com.movie.actor.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ActorName implements Serializable {
	private String name;

	public ActorName() {}
	
	public ActorName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
