package com.community.netflickers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.community.netflickers.entity.PostingHistory;

@Repository
public interface PostingHistoryRepository extends JpaRepository<PostingHistory,Long>{

}
