package com.community.netflickers.entity;

import com.community.netflickers.entity.auditing.BaseTimeEntity;

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
public class Comment extends BaseTimeEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long postId;
	
	private Long writer;
	
	private String content;
	
	@Column(length=1)
	private String deleteYn;
	
	@Builder
	public Comment(Long id, Long postId, Long writer, String content, String deleteYn) {
		
		this.id = id;
		this.postId = postId;
		this.writer = writer;
		this.content = content;
		this.deleteYn = deleteYn;
		
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}

}
