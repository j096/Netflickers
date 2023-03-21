package com.community.netflickers.service.dto;

import com.community.netflickers.entity.Comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto extends BaseTimeDto{
	
	private Long id;
	
	private Long postId;
	
	private String writer;
	
	private String content;
	
	private String deleteYn;
	
	@Builder
	public CommentDto(Comment comment) {
		this.id = comment.getId();
		
		this.postId = comment.getPostId();
		
		this.content = comment.getContent();
		
		this.deleteYn = comment.getDeleteYn();
		
		this.createdDate = toHhMmSsFormat(comment.getCreatedDate());
		
		this.modifiedDate = toHhMmSsFormat(comment.getModifiedDate());
	}
	
	public Comment toEntity() {
		return Comment.builder()
				.id(id)
				.postId(postId)
				.content(content)
				.deleteYn(deleteYn)
				.build();
	}

}
