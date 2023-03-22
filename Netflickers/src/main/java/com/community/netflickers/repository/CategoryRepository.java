package com.community.netflickers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.netflickers.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

}
