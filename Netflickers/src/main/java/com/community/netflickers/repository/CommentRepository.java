package com.community.netflickers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.community.netflickers.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
	
	@Query(value = "select c from Comment c where c.postId = :postId ")
	public List<Comment> findByPostId(@Param("postId") Long postId);
	
}
