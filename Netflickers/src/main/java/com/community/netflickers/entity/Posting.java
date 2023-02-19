package com.community.netflickers.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

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
public class Posting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long writer;
	
	private String title;
	
	private String content;
	
	private Long views;
	
	private String createdDate;	
	
	private String modifiedDate;
	
	
	@Builder
	public Posting(Long id, String title,Long writer, String content, Long views, String createdDate, String modifiedDate
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


	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

}
