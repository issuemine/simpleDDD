package com.movie.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.member.application.BuyMovieService;
import com.movie.member.application.JoinMemberService;
import com.movie.member.domain.Member;
import com.movie.member.domain.MemberId;
import com.movie.member.domain.MemberRequest;
import com.movie.member.repository.MemberRepository;
import com.movie.movie.application.DeleteMovieInfoService;
import com.movie.movie.application.MakeMovieInfoService;
import com.movie.movie.domain.Movie;
import com.movie.movie.domain.MovieRequest;
import com.movie.movie.exception.NotExistMovieException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BuyMovieTests {
	@Autowired
	BuyMovieService buyMovieService;
	
	@Autowired
	MakeMovieInfoService makeMovieInfoService;
	
	@Autowired
	DeleteMovieInfoService deleteMovieInfoService;
	
	@Autowired
	JoinMemberService joinMemberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Before
	public void setUp() {
		joinMemberService.joinMember(new MemberRequest("testId1@email.com", "testPassword1"));
	}
	
	@After
	public void afterTest() {
		Optional<Member> optionalMemberOne = memberRepository.findById(new MemberId("testId1@email.com"));
		optionalMemberOne.ifPresent(member -> memberRepository.delete(member));
	}
	
	@Test
	public void buyMovie() {
		Movie socialNetwork = makeMovieInfoService.makeMovieInfo(new MovieRequest("소셜네트워크", "마크 주커버그의 일대기", 10000, LocalDate.now()));
		buyMovieService.buyMovie("testId1@email.com", socialNetwork.getId());
		Optional<Member> memberOptional = memberRepository.findById(new MemberId("testId1@email.com"));
		if (memberOptional.isPresent()) {
			Member member = memberOptional.get();
			member.buyMovie(socialNetwork);
		}
		memberOptional = memberRepository.findById(new MemberId("testId1@email.com"));
		if (memberOptional.isPresent()) {
			Member member = memberOptional.get();
			assertThat(member.getMovies().size()).isEqualTo(1);
			memberRepository.delete(member);
		}
		deleteMovieInfoService.delete(socialNetwork.getId());
	}
	
	@Test(expected = NotExistMovieException.class)
	public void buyMovieWithoutMovie() {
		buyMovieService.buyMovie("testId1@email.com", -1L);
	}
}
