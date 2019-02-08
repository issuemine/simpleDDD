package com.movie.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.movie.member.domain.Member;
import com.movie.member.domain.MemberId;
import com.movie.member.domain.UserDetails;

@Controller
public class LoginMemberController {
	@RequestMapping(value = "/member", method = RequestMethod.POST)
	public ResponseEntity<?> member(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		Member member = new Member(new MemberId(userDetails.getEmail()), userDetails.getRole(), userDetails.getBlocked());
		return new ResponseEntity<>(member, HttpStatus.OK);
	}
}
