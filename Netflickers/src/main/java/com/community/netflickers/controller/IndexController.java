package com.community.netflickers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.community.netflickers.service.CategoryService;
import com.community.netflickers.service.dto.CategoryDto;

@Controller
public class IndexController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/")
	public String index(Model model) {
		List<CategoryDto> list = categoryService.getCategories();
		
		model.addAttribute("category", list);
		return "main";
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
