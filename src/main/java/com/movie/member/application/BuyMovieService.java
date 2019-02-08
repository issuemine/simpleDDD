package com.movie.member.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

import com.movie.member.domain.Member;
import com.movie.member.domain.MemberId;
import com.movie.member.event.MovieBoughtEvent;
import com.movie.member.repository.MemberRepository;
import com.movie.movie.domain.Movie;
import com.movie.movie.exception.NotExistMovieException;
import com.movie.movie.repository.MovieRepository;

@Service
public class BuyMovieService implements ApplicationEventPublisherAware {
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Transactional
	public Movie buyMovie(String email, Long movieId) {
		Optional<Member> memberOptional = memberRepository.findById(new MemberId(email));
		Optional<Movie> movieOptional = movieRepository.findById(movieId);
		
		if (memberOptional.isPresent() && movieOptional.isPresent()) {
			Member member = memberOptional.get();
			Movie movie = movieOptional.get();
			member.buyMovie(movie);
			memberRepository.save(member);
			applicationEventPublisher.publishEvent(new MovieBoughtEvent(this, email));
			return movie;
		}
		throw new NotExistMovieException();
	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}
}
