package com.movie.grade;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.grade.application.GiveGradeService;
import com.movie.grade.domain.Grade;
import com.movie.grade.domain.GradeRequest;
import com.movie.grade.repository.GradeRepository;
import com.movie.movie.application.DeleteMovieInfoService;
import com.movie.movie.application.MakeMovieInfoService;
import com.movie.movie.domain.Movie;
import com.movie.movie.domain.MovieRequest;
import com.movie.movie.exception.NotExistMovieException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GiveGradeServiceTests {
	@Autowired
	GiveGradeService giveGradeService;
	
	@Autowired
	GradeRepository gradeRepository;
	
	@Autowired
	MakeMovieInfoService makeMovieInfoService;
	
	@Autowired
	DeleteMovieInfoService deleteMovieInfoService;
	
	@Test
	public void giveGrade() {
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now()));
		
		Long gradeCount = gradeRepository.count();
		
		giveGradeService.giveGrade(new GradeRequest(
				socialNetwork.getId(),
				3.5), 
				"issuemine");
		assertThat(gradeRepository.count()).isEqualTo(gradeCount + 1);
		
		giveGradeService.giveGrade(new GradeRequest(
				socialNetwork.getId(),
				4.5), 
				"issuemine1");
		assertThat(gradeRepository.count()).isEqualTo(gradeCount + 2);
		
		deleteMovieInfoService.delete(socialNetwork.getId());
	}
	
	@Test(expected = NotExistMovieException.class)
	public void giveGradeWithNotExistMovie() {
		giveGradeService.giveGrade(new GradeRequest(
				-1L,
				3.5), 
				"issuemine");
	}
	
	@Test
	public void giveGradeAgain() {
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now()));
		
		Grade firstGrade = giveGradeService.giveGrade(new GradeRequest(
				socialNetwork.getId(),
				3.5), 
				"issuemine");
		
		assertThat(firstGrade.getGrade()).isEqualTo(3.5);
		
		Grade secondGrade = giveGradeService.giveGrade(new GradeRequest(
				socialNetwork.getId(),
				4.5), 
				"issuemine");
		assertThat(secondGrade.getGrade()).isEqualTo(4.5);
		
		deleteMovieInfoService.delete(socialNetwork.getId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void giveGradeWithoutReviewer() {
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now()));
		
		giveGradeService.giveGrade(new GradeRequest(
				socialNetwork.getId(),
				3.5), 
				"");
		
		deleteMovieInfoService.delete(socialNetwork.getId());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void giveGradeWithoutGrade() {
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now()));
		
		giveGradeService.giveGrade(new GradeRequest(
				socialNetwork.getId(),
				null), 
				"issuemine");
		
		deleteMovieInfoService.delete(socialNetwork.getId());
	}
}
