package com.community.netflickers.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.community.netflickers.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long>{
	
	@Query(value = "select c from Comment c where c.postId = :postId order by c.createdDate asc")
	public List<Comment> findByPostId(@Param("postId") Long postId,Pageable pageable);

	@Query(value = "select count(*) from Comment c where c.postId = :postId")
	public long getCountByPostId(@Param("postId") Long id);
	
}
