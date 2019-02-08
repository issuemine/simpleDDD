package com.movie.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.movie.member.application.JoinMemberService;
import com.movie.member.domain.Member;
import com.movie.member.domain.MemberId;
import com.movie.member.domain.MemberRequest;
import com.movie.member.exception.AlreadyExistMemberException;
import com.movie.member.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JoinMemberServiceTests {
	@Autowired
	JoinMemberService joinMemberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@After
	public void afterTest() {
		Optional<Member> optionalMemberOne = memberRepository.findById(new MemberId("testId1@email.com"));
		optionalMemberOne.ifPresent(member -> memberRepository.delete(member));
		Optional<Member> optionalMemberTwo = memberRepository.findById(new MemberId("testId2@email.com"));
		optionalMemberTwo.ifPresent(member -> memberRepository.delete(member));
	}
	
	@Test
	public void joinMember() {
		Long memberCount = memberRepository.count();
		
		joinMemberService.joinMember(new MemberRequest("testId1@email.com", "testPassword1"));
		
		assertThat(memberRepository.count()).isEqualTo(memberCount + 1);
		
		joinMemberService.joinMember(new MemberRequest("testId2@email.com", "testPassword2"));
		
		assertThat(memberRepository.count()).isEqualTo(memberCount + 2);
	}
	
	@Test(expected = AlreadyExistMemberException.class)
	public void duplicateJoinMember() {
		joinMemberService.joinMember(new MemberRequest("testId1@email.com", "testPassword1"));
		joinMemberService.joinMember(new MemberRequest("testId1@email.com", "testPassword1"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void joinMemberWithoutEmail() {
		joinMemberService.joinMember(new MemberRequest("", "testPassword1"));
	}
}
