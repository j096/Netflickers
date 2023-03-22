package com.community.netflickers.service.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JsonDto {

	private int page;
	
	@SerializedName("results")
	private List<CategoryDto> results;
}
