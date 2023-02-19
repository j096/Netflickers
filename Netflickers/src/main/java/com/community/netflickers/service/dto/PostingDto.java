package com.community.netflickers.service.dto;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.community.netflickers.entity.Posting;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class PostingDto {
	
	private Long id;
	
	private Long writer;
	
	private String title;
	
	private String content;
	
	private Long views;
	
	private LocalDateTime createdDate;	
	
	private LocalDateTime modifiedDate;
	
	@Builder
	public Posting toEntity() {
		return Posting.builder()
				.id(id)
				.writer(writer)
				.title(title)
				.content(content)
				.createdDate(createdDate)
				.modifiedDate(modifiedDate)
				.build();
	}
	
	
}
