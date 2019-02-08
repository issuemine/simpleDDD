package com.movie.member.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.member.domain.Member;
import com.movie.member.domain.MemberId;
import com.movie.member.domain.Password;
import com.movie.member.repository.MemberRepository;

@Service
public class PasswordChangeService {
	MemberRepository memberRepository;
	
	private PasswordEncoder passwordEncoder;
	
	public Member changePassword(MemberId memberId, String oldPassword, String newPassword) {
		Optional<Member> optionalMember = memberRepository.findById(memberId);
		optionalMember.ifPresent(member -> member.changePassword(oldPassword, newPassword, passwordEncoder));
		return optionalMember.get();
	}
	
	@Autowired
	public void setMemberRepository(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
