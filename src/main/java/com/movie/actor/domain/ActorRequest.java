package com.movie.actor.domain;

public class ActorRequest {
	String actorName;
	
	String actorInfo;
	
	public ActorRequest() { }
	
	public ActorRequest(String actorName, String actorInfo) {
		this.actorName = actorName;
		this.actorInfo = actorInfo;
	}

	public String getActorName() {
		return actorName;
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public String getActorInfo() {
		return actorInfo;
	}

	public void setActorInfo(String actorInfo) {
		this.actorInfo = actorInfo;
	}
}
