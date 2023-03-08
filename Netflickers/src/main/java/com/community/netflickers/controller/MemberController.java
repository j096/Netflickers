package com.community.netflickers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody MemberDto dto) {
		
		int isSaved = memberService.signup(dto);
		
		Message msg = new Message();
		if(isSaved==1) {
			msg.setMessage(messageSource.getMessage("msg.signup.success"));
			msg.setUrl(messageSource.getMessage("url.login"));
			return new ResponseEntity(msg,HttpStatus.CREATED);
		}else {
			if(isSaved == -1)//아이디중복
				msg.setMessage(messageSource.getMessage("msg.signup.fail.id"));
			else if(isSaved == -2)//이메일중복
				msg.setMessage(messageSource.getMessage("msg.signup.fail.email"));
			
			return new ResponseEntity(msg,HttpStatus.NOT_ACCEPTABLE);
		}
		
	}
	
	@PostMapping("/find")
	public ResponseEntity findLoginIdByEmail(@RequestBody MemberDto dto) {
		String loginId = memberService.findloginIdByEmail(dto.getEmail());
		
		Message msg = new Message();

		if(loginId == null)
			msg.setMessage("회원 정보가 존재하지 않습니다.");
		else
			msg.setMessage(messageSource.getMessage("msg.id.find.result",new String[] {loginId}));
	
		return new ResponseEntity(msg,HttpStatus.OK);
	}
	
	@PostMapping("/password")
	public ResponseEntity changePassword(@RequestBody MemberDto dto){
		
		boolean isSaved = memberService.chagePassword(dto);
		Message msg = new Message();
		if(isSaved) {
			msg.setMessage(messageSource.getMessage("msg.password.change.success"));
			msg.setUrl(messageSource.getMessage("url.login"));
		}else {
			msg.setMessage(messageSource.getMessage("msg.password.change.fail"));
			msg.setMessage(messageSource.getMessage("url.change.password"));
		}
		
		return new ResponseEntity(msg,HttpStatus.CREATED);
			
	}
	
}
