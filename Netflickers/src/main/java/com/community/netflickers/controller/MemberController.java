package com.community.netflickers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.netflickers.common.Message;
import com.community.netflickers.service.MemberDetailService;
import com.community.netflickers.service.dto.MemberDto;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberDetailService memberService;
	
	@Autowired
	private MessageSourceAccessor messageSource;
	
//	@GetMapping("/login")
//	public String memberLogin(@RequestBody MemberDto dto) {
//		memberService.loadUserByUsername(dto.getLoginId());
//	}
	
	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody MemberDto dto) {
		
		boolean isSaved = memberService.signup(dto);
		
		Message msg = new Message();
		if(isSaved) {
			msg.setMessage(messageSource.getMessage("msg.signup.success"));
			msg.setUrl(messageSource.getMessage("url.login"));
			return new ResponseEntity(msg,HttpStatus.CREATED);
		}else {//아이디중복
			msg.setMessage(messageSource.getMessage("msg.signup.fail"));
			return new ResponseEntity(msg,HttpStatus.NOT_ACCEPTABLE);
		}
		
	}

}