package com.movie.review.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.movie.domain.Movie;
import com.movie.movie.exception.NotExistMovieException;
import com.movie.movie.repository.MovieRepository;
import com.movie.review.domain.Content;
import com.movie.review.domain.Review;
import com.movie.review.repository.ReviewRepository;
import com.movie.review.repository.ReviewRequest;

@Service
public class WriteReviewService {
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	public Review writeReview(ReviewRequest reviewRequest, String writer) {
		Long movieId = reviewRequest.getMovieId();
		String content = reviewRequest.getContent();
		Optional<Movie> movieOptional = movieRepository.findById(movieId);
		if (movieOptional.isPresent()) {
			return reviewRepository.save(new Review(movieOptional.get(), new Content(writer, content)));
		}
		throw new NotExistMovieException();
	}
}
