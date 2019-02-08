package com.movie.member.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async
@Component
public class MovieBoughtEventListener implements ApplicationListener<MovieBoughtEvent> {
	@Override
	public void onApplicationEvent(MovieBoughtEvent event) {
		System.out.println("send email to : " + event.getEmail());
	}
}
