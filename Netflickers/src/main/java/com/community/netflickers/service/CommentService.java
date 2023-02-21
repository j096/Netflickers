package com.community.netflickers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.netflickers.common.DateTimeGenerator;
import com.community.netflickers.entity.Comment;
import com.community.netflickers.repository.CommentRepository;
import com.community.netflickers.service.dto.CommentDto;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepo;
	
	@Transactional
	public List<Comment> getPostComments(Long postId) {
		
		List<Comment> comments = commentRepo.findByPostId(postId);
		
		return comments;
	}


	@Transactional
	public Long saveComment(CommentDto dto) {
		
		dto.setCreatedDate(DateTimeGenerator.getNowDateTime());
		dto.setModifiedDate(DateTimeGenerator.getNowDateTime());

		
		return commentRepo.save(dto.toEntity()).getPostId();
	}

	@Transactional
	public Long updateComment(CommentDto dto) {
		
		Optional<Comment> find = commentRepo.findById(dto.getId());
		
		Comment comment = find.orElseThrow();
		
		comment.setModifiedDate(DateTimeGenerator.getNowDateTime());
		comment.setContent(dto.getContent());
		
		return comment.getPostId();
		
	}

	@Transactional
	public Long deletComment(Long id) {
		
		Optional<Comment> find = commentRepo.findById(id);
		
		Comment comment = find.orElseThrow();
		comment.setModifiedDate(DateTimeGenerator.getNowDateTime());
		comment.setDeleteYn("Y");
		comment.setContent("삭제된 댓글입니다.");
		
		return comment.getPostId();
	}


}
