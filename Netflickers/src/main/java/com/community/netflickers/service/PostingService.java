package com.community.netflickers.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	CommentService commentService;
	
	@Transactional
	public List<PostingDto> getPostList(Pageable pageable, String categoryId){
		return postRepo.findAll(pageable).stream().filter(p->p.getCategoryId().equals(categoryId)).map(PostingDto::new).collect(Collectors.toList());
	}
	
	@Transactional
	public PostingDto getPostById(Long id) {
		
		Optional<Posting> find = postRepo.findById(id);
		Posting post =  find.orElseThrow();
		post.setViews(post.getViews()+1);
		
		return new PostingDto(post);
	}


	@Transactional
	public Long savePost(PostingDto dto) {
		dto.setViews((long)0);
		
		return postRepo.save(dto.toEntity()).getId();
	}
	
	@Transactional
	public Long updatePost(PostingDto dto) {
		
		Optional<Posting> find = postRepo.findById(dto.getId());
		
		Posting post = find.orElseThrow();
		post.setContent(dto.getContent());
		post.setTitle(dto.getTitle());
		
		return post.getId();
				
	}

	@Transactional
	public void deletePost(Long id) {
		
		//1. 히스토리 테이블로 이동
		Optional<Posting> find = postRepo.findById(id);
		Posting post = find.orElseThrow();
		
		PostingHistory postHist = post.toHistory();
		postHistRepo.save(postHist);
		
		//2. 기존 테이블에서 삭제
		postRepo.delete(post);
		
		
		
	}

	public long getTotalCount() {
		return postRepo.count();
	}

}
