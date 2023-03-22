package com.community.netflickers.entity;

import com.community.netflickers.entity.auditing.BaseEntity;

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
public class PostingHistory extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String content;
	
	private Long views;
	
	private Long categoryId;
	
	@Builder
	public PostingHistory(Long id, String title, String content, Long views, Long categoryId) {
		
		this.id = id;
		
		this.title = title;
		
		this.content = content;
		
		this.views = views;
		
		this.categoryId = categoryId;

	}

}
