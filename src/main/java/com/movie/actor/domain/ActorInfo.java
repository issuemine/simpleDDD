package com.movie.actor.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ActorInfo implements Serializable {
	private String info;

	public ActorInfo() {}
	
	public ActorInfo(String info) {
		this.info = info;
	}
	
	public String getInfo() {
		return info;
	}
}
