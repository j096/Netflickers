package com.community.netflickers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.netflickers.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{

}
