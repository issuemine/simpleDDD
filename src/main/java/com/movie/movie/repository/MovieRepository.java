package com.movie.movie.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.movie.domain.Movie;
import com.movie.movie.domain.MovieTitle;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
	Optional<Movie> findByMovieTitle(MovieTitle movieTitle);
}
