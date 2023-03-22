package com.community.netflickers.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.netflickers.entity.Category;
import com.community.netflickers.repository.CategoryRepository;
import com.community.netflickers.service.dto.CategoryDto;
import com.community.netflickers.service.dto.PostingDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;

	@Transactional
	public void save(List<CategoryDto> results) {
		// TODO Auto-generated method stub
		
		List<Category> entities = results.stream().map(p-> new Category(p.getId(),p.getTitle(),p.getReleased(),p.getRating())).collect(Collectors.toList());
		categoryRepository.saveAllAndFlush(entities);
	}

	public List<CategoryDto> getCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
	}
	
}