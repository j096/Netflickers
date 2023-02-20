package com.community.netflickers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.netflickers.entity.PostingHistory;

public interface PostingHistoryRepository extends JpaRepository<PostingHistory,Long>{

}
