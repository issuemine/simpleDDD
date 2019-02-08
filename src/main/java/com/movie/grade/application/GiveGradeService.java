package com.movie.grade.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.grade.domain.Grade;
import com.movie.grade.domain.GradeId;
import com.movie.grade.domain.GradeRequest;
import com.movie.grade.repository.GradeRepository;
import com.movie.movie.domain.Movie;
import com.movie.movie.exception.NotExistMovieException;
import com.movie.movie.repository.MovieRepository;

@Service
public class GiveGradeService {
	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	public Grade giveGrade(GradeRequest gradeRequest, String reviewer) {
		Long movieId = gradeRequest.getMovieId();
		Optional<Movie> movieOptional = movieRepository.findById(movieId);
		if (movieOptional.isPresent()) {
			return gradeRepository.save(new Grade(new GradeId(movieOptional.get(), reviewer), gradeRequest.getGrade()));
		}
		throw new NotExistMovieException();
	}
}
