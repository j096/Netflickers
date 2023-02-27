package com.community.netflickers.service.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.community.netflickers.entity.Posting;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostingDto extends BaseTimeDto{
	
	private Long id;
	
	private Long writer;
	
	private String title;
	
	private String content;
	
	private Long views;
	
	
	@Builder
	public PostingDto(Posting posting) {
		this.id=posting.getId();
	
		this.writer=posting.getWriter();
		
		this.title=posting.getTitle();
		
		this.content=posting.getContent();
		
		this.views=posting.getViews();
		
		this.createdDate = toHhMmSsFormat(posting.getCreatedDate());
		
		this.modifiedDate = toHhMmSsFormat(posting.getModifiedDate());
		
//		this.comments = posting.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
		
	}
	
	
	@Builder
	public Posting toEntity() {
		return Posting.builder()
				.id(id)
				.writer(writer)
				.title(title)
				.content(content)
				.views(views)
				.build();
	}
	
	
}
