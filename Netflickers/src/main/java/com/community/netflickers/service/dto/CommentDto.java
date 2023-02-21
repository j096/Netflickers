package com.community.netflickers.service.dto;

import org.springframework.stereotype.Service;

import com.community.netflickers.entity.Comment;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class CommentDto {
	
	private Long id;
	
	private Long postId;
	
	private Long writer;
	
	private String content;
	
	private String createdDate;	
	private String modifiedDate;
	private String deletedDate;
	
	private String deleteYn;
	
	public Comment toEntity() {
		return Comment.builder()
				.id(id)
				.postId(postId)
				.writer(writer)
				.content(content)
				.createdDate(createdDate)
				.modifiedDate(modifiedDate)
				.deletedDate(deletedDate)
				.deleteYn(deleteYn)
				.build();
	}

}
