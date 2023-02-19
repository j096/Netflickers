package com.community.netflickers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.netflickers.entity.Posting;
import com.community.netflickers.service.PostingService;
import com.community.netflickers.service.dto.PostingDto;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostingService postService;
	
	@GetMapping("/list")
	public String postings(Model model) {
		List<Posting> postings = postService.getPostList();
		model.addAttribute("postings", postings);
		return "postList";
	}
	
	@GetMapping("/write")
	public String write() {
		return "post-write";
	}
	
	@PostMapping("/save")
	public ResponseEntity save(@RequestBody PostingDto dto) {
		postService.savePost(dto);
		return new ResponseEntity(HttpStatus.CREATED);
	}

}
