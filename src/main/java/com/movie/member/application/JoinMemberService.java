package com.movie.member.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.member.constant.Role;
import com.movie.member.domain.Member;
import com.movie.member.domain.MemberId;
import com.movie.member.domain.MemberRequest;
import com.movie.member.domain.Password;
import com.movie.member.exception.AlreadyExistMemberException;
import com.movie.member.repository.MemberRepository;

@Service
public class JoinMemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Member joinMember(MemberRequest memberRequest) {
		String email = memberRequest.getEmail();
		String password = memberRequest.getPassword();
		
		Optional<Member> optionalMember = memberRepository.findById(new MemberId(email));
		
		if (optionalMember.isPresent()) {
			throw new AlreadyExistMemberException(email);
		}
		
		Member member = new Member(new MemberId(email), new Password(passwordEncoder.encode(password)), Role.ROLE_USER);
		return memberRepository.save(member);
	}
}
