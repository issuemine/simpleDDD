package com.movie.movie.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movie.common.constant.ErrorCode;
import com.movie.common.model.ErrorDetail;
import com.movie.movie.domain.Movie;
import com.movie.movie.repository.MovieRepository;

@Controller
public class MovieController {
	@Autowired
	private MovieRepository movieRepository;
	
	@RequestMapping(value = "/movie/info/{movieId}", method = RequestMethod.GET)
	public ResponseEntity<?> movieInfo(@PathVariable Long movieId) {
		Optional<Movie> movieOptional = movieRepository.findById(movieId);
		if (!movieOptional.isPresent()) {
			return new ResponseEntity<>(new ErrorDetail(ErrorCode.NOT_EXIST_MOVIE_ERROR.getCode(), "Not Exist Movie"), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(movieOptional.get(), HttpStatus.OK);
	}
}
