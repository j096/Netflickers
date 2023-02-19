package com.community.netflickers.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table
@Getter
public class Posting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long writer;
	
	private String title;
	
	private String content;
	
	private Long views;
	
	private LocalDateTime createdDate;	
	
	private LocalDateTime modifiedDate;
	
	
	@Builder
	public Posting(Long id, String title,Long writer, String content, Long views, LocalDateTime createdDate, LocalDateTime modifiedDate
			) {
		
		this.id = id;
		
		this.title = title;
		
		this.writer = writer;
		
		this.content = content;
		
		this.views = views;

		this.createdDate = createdDate;

		this.modifiedDate = modifiedDate;

	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

}
