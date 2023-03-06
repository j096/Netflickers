package com.community.netflickers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.community.netflickers.entity.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{
	
	@Query(value="select m from Member m where m.loginId = :loginId")
	Member findByLoginId(@Param("loginId") String loginId);
	
	@Query(value="select count(*) from Member m where m.loginId = :loginId")
	Long existsByLoginId(@Param("loginId") String loginId);
	
	@Query(value="select m.loginId from Member m where m.email = :email")
	String findLoginIdByEmail(@Param("email") String email);

}
