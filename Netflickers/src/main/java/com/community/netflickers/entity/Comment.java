package com.community.netflickers.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long postId;
	
	private Long writer;
	
	private String content;
	
	@Column(length=19)
	private String createdDate;	
	@Column(length=19)
	private String modifiedDate;
	
	@Column(length=1)
	private String deleteYn;
	
	@Builder
	public Comment(Long id, Long postId, Long writer, String content, String createdDate, String modifiedDate, String deletedDate, String deleteYn) {
		
		this.id = id;
		this.postId = postId;
		this.writer = writer;
		this.content = content;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.deleteYn = deleteYn;
		
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

}
