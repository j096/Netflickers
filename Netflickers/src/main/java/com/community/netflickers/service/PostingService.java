package com.community.netflickers.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.netflickers.entity.Posting;
import com.community.netflickers.repository.PostingRepository;
import com.community.netflickers.service.dto.PostingDto;

@Service
public class PostingService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PostingRepository postRepo;
	
	@Transactional
	public List<Posting> getPostList(){
		return postRepo.findAll();
	}

	@Transactional
	public void savePost(PostingDto dto) {
		dto.setCreatedDate(LocalDateTime.now());
		dto.setViews((long)0);
		postRepo.save(dto.toEntity());
		
	}
	
	@Transactional
	public Long modifyPost(PostingDto dto) {
		
		Optional<Posting> find = postRepo.findById(dto.getId());
		
		Posting post = find.get();
		
		post.setContent(dto.getContent());
		post.setModifiedDate(LocalDateTime.now());
		post.setTitle(dto.getTitle());
		
		return post.getId();
				
	}

}
