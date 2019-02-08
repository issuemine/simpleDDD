package com.movie.review;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.movie.application.DeleteMovieInfoService;
import com.movie.movie.application.MakeMovieInfoService;
import com.movie.movie.domain.Movie;
import com.movie.movie.domain.MovieRequest;
import com.movie.movie.exception.NotExistMovieException;
import com.movie.review.application.WriteReviewService;
import com.movie.review.domain.Review;
import com.movie.review.repository.ReviewRepository;
import com.movie.review.repository.ReviewRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WriteReviewServiceTests {
	@Autowired
	WriteReviewService writeReviewService;
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	MakeMovieInfoService makeMovieInfoService;
	
	@Autowired
	DeleteMovieInfoService deleteMovieInfoService;
	
	@Test
	public void writeReview() {
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now()));
		
		Long reviewCount = reviewRepository.count();
		
		writeReviewService.writeReview(new ReviewRequest(
				socialNetwork.getId(),
				"이 영화는 개발자들이 보기이 보면 감동적인 영화입니다."),
				"issuemine");
		
		assertThat(reviewRepository.count()).isEqualTo(reviewCount + 1);
		
		writeReviewService.writeReview(new ReviewRequest(
				socialNetwork.getId(),
				"주커버그는 과연 옳았을까?"),
				"issuemine1");
		
		assertThat(reviewRepository.count()).isEqualTo(reviewCount + 2);
		
		deleteMovieInfoService.delete(socialNetwork.getId());
	}
	
	@Test(expected = NotExistMovieException.class)
	public void writeReviewWithNotExistMovieId() {
		writeReviewService.writeReview(new ReviewRequest(
				-1L,
				"이 영화는 개발자들이 보기이 보면 감동적인 영화입니다."),
				"issuemine");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void writeReviewWithoutContent() {
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now()));
		
		writeReviewService.writeReview(new ReviewRequest(
				socialNetwork.getId(),
				""),
				"issuemine");
		
		deleteMovieInfoService.delete(socialNetwork.getId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void writeReviewWithoutWriter() {
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now()));
		
		writeReviewService.writeReview(new ReviewRequest(
				socialNetwork.getId(),
				"이 영화는 개발자들이 보기이 보면 감동적인 영화입니다."),
				"");
		
		deleteMovieInfoService.delete(socialNetwork.getId());
	}
}
