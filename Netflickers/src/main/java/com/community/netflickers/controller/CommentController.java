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
import com.community.netflickers.entity.Comment;
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
	public String comments(@PathVariable Long postId, Model model) {
		List<Comment> comments = commentService.getPostComments(postId);
		
		model.addAttribute("comments",comments);
		
		return "layout/comment-list";
	}
	
	@PostMapping("/save")
	public ResponseEntity save(@RequestBody CommentDto dto) {
		Long postId = commentService.saveComment(dto);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.comment.save",Locale.KOREA));
		msg.setUrl(messageSource.getMessage("url.comment.list", new Long[] {postId},Locale.KOREA));
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody CommentDto dto) {
		dto.setId(id);
		Long postId = commentService.updateComment(dto);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.comment.update",Locale.KOREA));
		msg.setUrl(messageSource.getMessage("url.comment.list", new Long[] {postId},Locale.KOREA));
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		Long postId = commentService.deletComment(id);
		Message msg = new Message();
		msg.setMessage(messageSource.getMessage("msg.comment.delete",Locale.KOREA));
		msg.setUrl(messageSource.getMessage("url.comment.list", new Long[] {postId},Locale.KOREA));
		return new ResponseEntity(msg,HttpStatus.OK);
	}

}
