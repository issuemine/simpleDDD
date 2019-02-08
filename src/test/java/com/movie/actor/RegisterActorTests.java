package com.movie.actor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.actor.application.RegisterActorService;
import com.movie.actor.domain.Actor;
import com.movie.actor.domain.ActorRequest;
import com.movie.actor.repository.ActorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterActorTests {
	@Autowired
	RegisterActorService registerActorService;
	
	@Autowired
	ActorRepository actorRepository;
	
	@Test
	public void registerActor() {
		Long actorCount = actorRepository.count();
		
		Actor ha = registerActorService.registerActor(new ActorRequest("하정우", "하정우는 대한민국의 배우이다."));
		
		assertThat(actorRepository.count()).isEqualTo(actorCount + 1);
		
		Actor kim = registerActorService.registerActor(new ActorRequest("김혜수", "김헤수는 연기력으로 누구에게도 지지 않는 배우 중 한명이다."));
		
		assertThat(actorRepository.count()).isEqualTo(actorCount + 2);
		
		actorRepository.delete(ha);
		actorRepository.delete(kim);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void registerActorWithoutName() {
		registerActorService.registerActor(new ActorRequest("", "하정우는 대한민국의 배우이다."));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void registerActorWithoutInfo() {
		registerActorService.registerActor(new ActorRequest("하정우", ""));
	}
}
