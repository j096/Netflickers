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
import org.springframework.web.bind.annotation.RequestParam;

import com.community.netflickers.common.Message;
import com.community.netflickers.common.PageNumberGenerator;
import com.community.netflickers.service.CommentService;
import com.community.netflickers.service.dto.CommentDto;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	private MessageSourceAccessor messageSource;
	
	@GetMapping("/list/{postId}")
	public String comments(@PathVariable Long postId, Pageable pageable, @RequestParam(required=false) PageNumberGenerator pageNumber, Model model) {
		List<CommentDto> comments = commentService.getPostComments(postId, pageable);
		
		if(pageNumber == null)
			pageNumber = new PageNumberGenerator();
		
		pageNumber.calNumberButton(commentService.getTotalCountByPostId(postId), pageable.getPageNumber(), pageable.getPageSize());
		
		model.addAttribute("comments",comments);
		model.addAttribute("commentPaging",pageNumber);
		
		return "layout/comment-list";
	}
	
	@PostMapping("/save")
	public ResponseEntity save(@RequestBody CommentDto dto) {
		Long postId = commentService.saveComment(dto);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.comment.save"));
		msg.setUrl(messageSource.getMessage("url.comment.list", new Long[] {postId})+"?page=0");
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody CommentDto dto) {
		dto.setId(id);
		Long postId = commentService.updateComment(dto);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.comment.update"));
		msg.setUrl(messageSource.getMessage("url.comment.list", new Long[] {postId})+"?page=0");
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable Long id, Pageable pageable) {
		Long postId = commentService.deletComment(id);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.comment.delete"));
		msg.setUrl(messageSource.getMessage("url.comment.list", new Long[] {postId})+"?page=0");
		return new ResponseEntity(msg,HttpStatus.OK);
	}

}
