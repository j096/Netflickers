package com.community.netflickers.service.dto;

import java.util.List;

import com.community.netflickers.entity.Category;
import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto extends BaseTimeDto {
	@SerializedName("imdbid")
	private String id;

	private String title;

	private Long released;

	@SerializedName("imdbrating")
	private Double rating;

//	@SerializedName("imageurl")
//	private List<String> imageUrl;

	@Builder
	public CategoryDto(Category category) {
		this.id = category.getId();

		this.title = category.getTitle();

		this.released = category.getReleased();

		this.rating = category.getRating();

//		this.imageUrl.add(category.getImageUrl());
	}

	public Category toEntity() {
		return Category.builder().id(id).title(title).released(released).rating(rating)
//				.imageUrl(imageUrl.get(0))
				.build();
	}

}
