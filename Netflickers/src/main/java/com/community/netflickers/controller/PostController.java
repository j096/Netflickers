package com.community.netflickers.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Pageable;
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
import com.community.netflickers.common.PageNumberGenerator;
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
	public String postings(Pageable pageable, Model model) {
		List<PostingDto> postings = postService.getPostList(pageable);
		
		PageNumberGenerator pagingNumber = new PageNumberGenerator();
		pagingNumber.CalNumberButton(postService.getTotalCount(), pageable.getPageSize(), pageable.getPageNumber());
		
		model.addAttribute("postings", postings);
		model.addAttribute("paging", pagingNumber);
		
		return "post-list";
	}
	
	@GetMapping("/read/{id}")
	public String read(@PathVariable Long id, Model model) {
		PostingDto post = postService.getPostById(id);
		model.addAttribute("post", post);
		return "post-read";
	}
	
	@GetMapping("/write")
	public String write() {
		return "post-write";
	}
	
	@GetMapping("/write/{id}")
	public String write(@PathVariable Long id, Model model) {
		PostingDto post = postService.getPostById(id);
		model.addAttribute("post", post);
		return "post-write";
	}
	
	@PostMapping("/save")
	public ResponseEntity save(@RequestBody PostingDto dto) {
		Long id = postService.savePost(dto);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.post.save"));
		msg.setUrl(messageSource.getMessage("url.post.read", new Long[] {id}));
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody PostingDto dto) {
		dto.setId(id);
		postService.updatePost(dto);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.post.update"));
		msg.setUrl(messageSource.getMessage("url.post.read", new Long[] {id}));
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity save(@PathVariable Long id) {
		postService.deletePost(id);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.post.delete"));
		msg.setUrl(messageSource.getMessage("url.post.list"));
		return new ResponseEntity(msg,HttpStatus.OK);
	}


}
