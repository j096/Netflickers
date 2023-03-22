package com.community.netflickers.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class Category {
	
	@Id
	private String id;
    
	private String title;
	
	private Long released;
	
	private Double rating;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name="categoryId")
	private List<Posting> postings = new ArrayList<>();
    
	@Builder
	public Category(String id, String title , Long released, Double rating) {
		
		this.id = id;
		this.title=title;
		this.released=released;
		this.rating=rating;
//		this.imageUrl=imageUrl;
	}


}
