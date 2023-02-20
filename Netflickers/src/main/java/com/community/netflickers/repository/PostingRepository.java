package com.community.netflickers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.community.netflickers.entity.Posting;

public interface PostingRepository extends JpaRepository<Posting,Long>{
	
	@Modifying
	@Query(value = "update Posting p set p.views = p.views+1 where p.id = :id ")
	public void updateViewsById(@Param("id") Long id);
}
