package com.movie.actor.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.actor.domain.Actor;
import com.movie.actor.domain.ActorInfo;
import com.movie.actor.domain.ActorName;
import com.movie.actor.domain.ActorRequest;
import com.movie.actor.repository.ActorRepository;

@Service
public class RegisterActorService {
	@Autowired
	ActorRepository actorRepository;
	
	public Actor registerActor(ActorRequest actorRequest) {
		Actor actor = new Actor(new ActorName(actorRequest.getActorName()), new ActorInfo(actorRequest.getActorInfo()));
		return actorRepository.save(actor);
	}
}
