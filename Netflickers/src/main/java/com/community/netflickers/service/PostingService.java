package com.community.netflickers.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.netflickers.common.DateTimeGenerator;
import com.community.netflickers.entity.Posting;
import com.community.netflickers.entity.PostingHistory;
import com.community.netflickers.repository.PostingHistoryRepository;
import com.community.netflickers.repository.PostingRepository;
import com.community.netflickers.service.dto.PostingDto;

@Service
public class PostingService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PostingRepository postRepo;
	
	@Autowired
	PostingHistoryRepository postHistRepo;
	
	@Autowired
	DateTimeGenerator dateTimeGenerator;
	
	@Transactional
	public List<Posting> getPostList(){
		return postRepo.findAll();
	}
	
	@Transactional
	public Posting getPostById(Long id) {
		
		Optional<Posting> find = postRepo.findById(id);
		Posting post =  find.orElseThrow();
		//조회수 증가
		postRepo.updateViewsById(id);
		
		return post;
	}


	@Transactional
	public Long savePost(PostingDto dto) {
		dto.setCreatedDate(dateTimeGenerator.getNowDateTime());
		dto.setViews((long)0);
		
		return postRepo.save(dto.toEntity()).getId();
	}
	
	@Transactional
	public Long updatePost(PostingDto dto) {
		
		Optional<Posting> find = postRepo.findById(dto.getId());
		
		Posting post = find.orElseThrow();
		
		post.setContent(dto.getContent());
		post.setModifiedDate(dateTimeGenerator.getNowDateTime());
		post.setTitle(dto.getTitle());
		
		return post.getId();
				
	}

	@Transactional
	public void deletePost(Long id) {
		
		//1. 히스토리 테이블로 이동
		Optional<Posting> find = postRepo.findById(id);
		Posting post = find.orElseThrow();
		
		PostingHistory postHist = post.toHistory(dateTimeGenerator.getNowDateTime());
		postHistRepo.save(postHist);
		
		//2. 기존 테이블에서 삭제
		postRepo.delete(post);
		
		
	}

}
