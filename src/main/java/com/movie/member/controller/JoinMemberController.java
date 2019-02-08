package com.movie.member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.movie.common.constant.ErrorCode;
import com.movie.common.model.ErrorDetail;
import com.movie.member.application.JoinMemberService;
import com.movie.member.domain.MemberRequest;
import com.movie.member.exception.AlreadyExistMemberException;

@Controller
public class JoinMemberController {
	@Autowired
	private JoinMemberService joinMemberService;
	
	@ResponseBody
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
	public ResponseEntity<?> memberJoin(@Valid MemberRequest memberRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			String errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
			return new ResponseEntity<>(new ErrorDetail(ErrorCode.VALIDATION_LOGIN_ERROR.getCode(), errorMessage), HttpStatus.BAD_REQUEST);
		}
		
		try {
			joinMemberService.joinMember(memberRequest);
		} catch (AlreadyExistMemberException ae) {
			return new ResponseEntity<>(new ErrorDetail(ErrorCode.EXIST_MEMBER_ERROR.getCode(), ae.getEmail()), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
