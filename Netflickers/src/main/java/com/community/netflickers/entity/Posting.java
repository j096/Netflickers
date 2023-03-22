package com.community.netflickers.entity;

import java.util.ArrayList;
import java.util.List;

import com.community.netflickers.entity.auditing.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posting extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String content;
	
	private Long views;
	
	private Long categoryId;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name="postId")
	private List<Comment> comments = new ArrayList<>();
	
	
	@Builder
	public Posting(Long id, String title, String content, Long views, Long categoryId) {
		
		this.id = id;
		
		this.title = title;
		
		this.content = content;
		
		this.views = views;
		
		this.categoryId = categoryId;
		
	}
	

	public void setTitle(String title) {
		this.title = title;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public void setViews(Long views) {
		this.views = views;
	}
	
	public PostingHistory toHistory () {
		
		return PostingHistory.builder()
		.id(id)
		.title(title)
		.content(content)
		.views(views)
		.categoryId(categoryId)
		.build();

	}


	

}
