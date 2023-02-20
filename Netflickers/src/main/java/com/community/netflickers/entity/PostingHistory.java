package com.community.netflickers.entity;

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
public class PostingHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long writer;
	
	private String title;
	
	private String content;
	
	private Long views;
	
	private String createdDate;	
	
	private String modifiedDate;
	
	private String deletedDate;
	
	@Builder
	public PostingHistory(Long id, String title,Long writer, String content, Long views, String createdDate, String modifiedDate
			,String deletedDate) {
		
		this.id = id;
		
		this.title = title;
		
		this.writer = writer;
		
		this.content = content;
		
		this.views = views;

		this.createdDate = createdDate;

		this.modifiedDate = modifiedDate;
		
		this.deletedDate = deletedDate;

	}

	public void setDeletedDate(String deletedDate) {
		this.deletedDate = deletedDate;
	}

}
