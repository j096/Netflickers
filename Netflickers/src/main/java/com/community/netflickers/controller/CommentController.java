package com.community.netflickers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.community.netflickers.common.Message;
import com.community.netflickers.service.CommentService;
import com.community.netflickers.service.dto.CommentDto;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/save")
	public ResponseEntity save(@RequestBody CommentDto dto) {
		Long postId = commentService.saveComment(dto);
		Message msg = new Message();
		msg.setMessage("댓글이 등록되었습니다.");
		msg.setUrl("/post/read/"+postId);
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity update(@PathVariable Long id, @RequestBody CommentDto dto) {
		dto.setId(id);
		Long postId = commentService.updateComment(dto);
		Message msg = new Message();
		msg.setMessage("댓글이 등록되었습니다.");
		msg.setUrl("/post/read/"+postId);
		return new ResponseEntity(msg,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		Long postId = commentService.deletComment(id);
		Message msg = new Message();
		msg.setMessage("댓글이 삭제되었습니다.");
		msg.setUrl("/post/read/"+postId);
		return new ResponseEntity(msg,HttpStatus.OK);
	}

}
