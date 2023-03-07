package com.community.netflickers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/login")
	public String loginPage(@RequestParam(value = "error", required = false)String error
			,@RequestParam(value = "exception", required = false)String exception, Model model) {
		
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		
		return "login";
	}
	
	@GetMapping("/signup")
	public String signupPage() {
		return "signup";
	}
	
	@GetMapping("/id-find")
	public String idFindPage() {
		return "id-find";
	}
}
