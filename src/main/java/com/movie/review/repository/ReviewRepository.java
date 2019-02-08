package com.movie.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.review.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
