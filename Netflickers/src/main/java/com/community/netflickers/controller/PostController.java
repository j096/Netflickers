package com.community.netflickers.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.netflickers.common.Message;
import com.community.netflickers.entity.Posting;
import com.community.netflickers.service.PostingService;
import com.community.netflickers.service.dto.PostingDto;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostingService postService;
	
	@Autowired
	private MessageSourceAccessor messageSource;
	
	@GetMapping("/list")
	public String postings(Model model) {
		List<Posting> postings = postService.getPostList();
		model.addAttribute("postings", postings);
		return "post-list";
	}
	
	@GetMapping("/read/{id}")
	public String read(@PathVariable Long id, Model model) {
		Posting post = postService.getPostById(id);
		model.addAttribute("post", post);
		return "post-read";
	}
	
	@GetMapping("/write")
	public String write() {
		return "post-write";
	}
	
	@GetMapping("/write/{id}")
	public String write(@PathVariable Long id, Model model) {
		Posting post = postService.getPostById(id);
		model.addAttribute("post", post);
		return "post-write";
	}
	
	@PostMapping("/save")
	public ResponseEntity save(@RequestBody PostingDto dto) {
		Long id = postService.savePost(dto);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.post.save",Locale.KOREA));
		msg.setUrl(messageSource.getMessage("url.post.read", new Long[] {id},Locale.KOREA));
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody PostingDto dto) {
		dto.setId(id);
		postService.updatePost(dto);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.post.update",Locale.KOREA));
		msg.setUrl(messageSource.getMessage("url.post.read", new Long[] {id},Locale.KOREA));
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity save(@PathVariable Long id) {
		postService.deletePost(id);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.post.delete",Locale.KOREA));
		msg.setUrl(messageSource.getMessage("url.post.list",Locale.KOREA));
		return new ResponseEntity(msg,HttpStatus.OK);
	}


}
