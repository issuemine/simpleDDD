package com.movie.movie;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.movie.application.MakeMovieInfoService;
import com.movie.movie.domain.Movie;
import com.movie.movie.domain.MovieRequest;
import com.movie.movie.repository.MovieRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieControllerTests {
    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;
    
    @Autowired
    private MakeMovieInfoService makeMovieInfoService;
    
    @Autowired
    private MovieRepository movieRepository;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void movieInfo() throws Exception {
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now()));
		
		MvcResult result = mockMvc.perform(get("/movie/info/" + socialNetwork.getId()))
		.andExpect(status().isOk())
		.andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		ObjectMapper mapper = new ObjectMapper();
		Movie movie = mapper.readValue(response.getContentAsString(), Movie.class);
		
		assertThat(movie.getId()).isEqualTo(socialNetwork.getId());
		movieRepository.delete(socialNetwork);
	}
}
