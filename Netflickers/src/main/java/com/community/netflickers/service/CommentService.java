package com.community.netflickers.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.netflickers.entity.Comment;
import com.community.netflickers.repository.CommentRepository;
import com.community.netflickers.service.dto.CommentDto;

@Service
public class CommentService {
	
	@Autowired
	CommentRepository commentRepo;
	
	@Transactional
	public List<CommentDto> getPostComments(Long postId, Pageable pageable) {
		
		return commentRepo.findByPostId(postId, pageable)
				.stream().map(CommentDto::new).collect(Collectors.toList());
	}


	@Transactional
	public Long saveComment(CommentDto dto) {
		
		return commentRepo.save(dto.toEntity()).getPostId();
	}

	@Transactional
	public Long updateComment(CommentDto dto) {
		
		Optional<Comment> find = commentRepo.findById(dto.getId());
		
		Comment comment = find.orElseThrow();
		comment.setContent(dto.getContent());
		
		return comment.getPostId();
		
	}

	@Transactional
	public Long deletComment(Long id) {
		
		Optional<Comment> find = commentRepo.findById(id);
		
		Comment comment = find.orElseThrow();
		comment.setDeleteYn("Y");
		comment.setContent("삭제된 댓글입니다.");
		
		return comment.getPostId();
	}


	public long getTotalCountByPostId(Long id) {
		return commentRepo.getCountByPostId(id);
	}

}
