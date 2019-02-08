package com.movie.movie.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movie.domain.Movie;
import com.movie.movie.domain.MovieInfo;
import com.movie.movie.domain.MovieRequest;
import com.movie.movie.domain.MovieTitle;
import com.movie.movie.domain.Price;
import com.movie.movie.repository.MovieRepository;

@Service
public class MakeMovieInfoService {
	@Autowired
	MovieRepository movieRepository;
	
	public Movie makeMovieInfo(MovieRequest movieRequest) {
		Movie movie = new Movie(new MovieTitle(movieRequest.getTitle()), new MovieInfo(movieRequest.getInfo()), 
				new Price(movieRequest.getPrice()), false, movieRequest.getReleaseDate(), movieRequest.getCategorys());
		return movieRepository.save(movie);
	}
}
