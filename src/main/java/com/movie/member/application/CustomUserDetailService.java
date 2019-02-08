package com.movie.member.application;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movie.member.domain.MemberId;
import com.movie.member.domain.UserDetails;
import com.movie.member.repository.MemberRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return Optional.ofNullable(memberRepository.findById(new MemberId(email)))
				.filter(member -> member.isPresent())
				.map(member -> new UserDetails(member.get()))
				.get();
	}
	
}
