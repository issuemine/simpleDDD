package com.movie.member.domain;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.movie.member.constant.Role;

public class UserDetails extends User {
	private String email;
	
	private Role role;
	
	private Boolean blocked;
	
	public UserDetails(Member member) {
		super(member.getMemberId().getEmail(), 
				member.getPassword().getPassword(), 
				Collections.singletonList(new SimpleGrantedAuthority(member.getRole().name())));
		this.setEmail(member.getMemberId().getEmail());
		this.setBlocked(member.getBlocked());
		this.setRole(member.getRole());
	}

	public String getEmail() {
		return email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	private void setRole(Role role) {
		this.role = role;
	}

	public Boolean getBlocked() {
		return blocked;
	}

	private void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
}
