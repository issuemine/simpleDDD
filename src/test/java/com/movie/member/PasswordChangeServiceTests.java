package com.movie.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.member.application.PasswordChangeService;
import com.movie.member.constant.Role;
import com.movie.member.domain.Member;
import com.movie.member.domain.MemberId;
import com.movie.member.domain.Password;
import com.movie.member.exception.PasswordNotMatchingException;
import com.movie.member.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordChangeServiceTests {
	PasswordChangeService passwordChangeService;
	
	MemberRepository mockMemberRepository;
	
	MemberId memberId = new MemberId("test1@email.com");
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Before
	public void setUp() {
		passwordChangeService = new PasswordChangeService();
		mockMemberRepository = mock(MemberRepository.class);
		passwordChangeService.setMemberRepository(mockMemberRepository);
		passwordChangeService.setPasswordEncoder(passwordEncoder);
		
		when(mockMemberRepository.findById(memberId))
		.thenReturn(Optional.of(new Member(memberId, new Password(passwordEncoder.encode("passwordTest1")), Role.ROLE_USER)));
	}
	
	@Test
	public void changePassword() {
		Member member = passwordChangeService.changePassword(memberId, "passwordTest1", "passwordTest2");
		assertThat(passwordEncoder.matches("passwordTest2", member.getPassword().getPassword()));
	}
	
	@Test(expected = PasswordNotMatchingException.class)
	public void changeUsingNoMatchingPassword() {
		passwordChangeService.changePassword(memberId, "passwordTest2", "passwordTest2");
	}
}
