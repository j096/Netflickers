package com.community.netflickers.entity;

import java.util.ArrayList;
import java.util.List;

import com.community.netflickers.entity.auditing.BaseTimeEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Posting extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long writer;
	
	private String title;
	
	private String content;
	
	private Long views;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name="postId")
	private List<Comment> comments = new ArrayList<>();
	
	
	@Builder
	public Posting(Long id, String title,Long writer, String content, Long views) {
		
		this.id = id;
		
		this.title = title;
		
		this.writer = writer;
		
		this.content = content;
		
		this.views = views;
		
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
		.writer(writer)
		.content(content)
		.views(views)
		.build();

	}


	

}
