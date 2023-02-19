package com.community.netflickers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.community.netflickers.entity.Posting;

public interface PostingRepository extends JpaRepository<Posting,Long>{

}
