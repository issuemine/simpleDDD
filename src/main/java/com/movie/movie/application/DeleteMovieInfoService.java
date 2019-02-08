package com.movie.movie.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movie.repository.MovieRepository;

@Service
public class DeleteMovieInfoService {
	@Autowired
	MovieRepository movieRepository;
	
	public void delete(Long movieId) {
		movieRepository.deleteById(movieId);
	}
}
