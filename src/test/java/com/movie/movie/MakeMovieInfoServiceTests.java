package com.movie.movie;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.category.domain.Category;
import com.movie.category.domain.CategoryId;
import com.movie.category.repository.CategoryRepository;
import com.movie.movie.application.MakeMovieInfoService;
import com.movie.movie.domain.Movie;
import com.movie.movie.domain.MovieRequest;
import com.movie.movie.repository.MovieRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MakeMovieInfoServiceTests {
	@Autowired
	MakeMovieInfoService makeMovieInfoService;
	
	@Autowired
	MovieRepository movieRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Test
	public void makeMovieInfo() {
		Long movieCount = movieRepository.count();
		
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now()));
		
		assertThat(movieRepository.count()).isEqualTo(movieCount + 1);
		
		Movie siliconValley = makeMovieInfoService.makeMovieInfo(new MovieRequest("실리콘밸리", "실리콘밸리에서 일어나는 일상을 그린 코미디 영화", 15000, LocalDate.now()));
		
		assertThat(movieRepository.count()).isEqualTo(movieCount + 2);
		
		movieRepository.delete(socialNetwork);
		movieRepository.delete(siliconValley);
	}
	
	@Test
	public void makeMovieInfoWithCategory() {
		Long movieCount = movieRepository.count();
		
		if (!categoryRepository.existsById(new CategoryId("드라마_테스트"))) {
			categoryRepository.save(new Category(new CategoryId("드라마_테스트")));
		}
		
		if (!categoryRepository.existsById(new CategoryId("코미디_테스트"))) {
			categoryRepository.save(new Category(new CategoryId("코미디_테스트")));
		}
		
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(
				new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now(), Arrays.asList("드라마_테스트"))
				);
		
		assertThat(movieRepository.count()).isEqualTo(movieCount + 1);
		
		Movie siliconValley = makeMovieInfoService.makeMovieInfo(
				new MovieRequest("실리콘밸리", "실리콘밸리에서 일어나는 일상을 그린 코미디 영화", 15000, LocalDate.now(), Arrays.asList("코미디_테스트", "드라마_테스트")));
		
		assertThat(movieRepository.count()).isEqualTo(movieCount + 2);
		
		movieRepository.delete(socialNetwork);
		movieRepository.delete(siliconValley);
		categoryRepository.deleteById(new CategoryId("코미디_테스트"));
		categoryRepository.deleteById(new CategoryId("드라마_테스트"));
	}
}
