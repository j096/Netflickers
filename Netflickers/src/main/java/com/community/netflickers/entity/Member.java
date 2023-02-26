package com.community.netflickers.entity;

import java.util.ArrayList;
import java.util.List;

import com.community.netflickers.entity.auditing.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity{
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String loginId; // 로그인시 받는 id값
    
    @Column
    private String password; // 로그인시 받는 password값
    
    @Column
    private String nickname;
    
    @Column
    private String email;
    
    @OneToMany
    @JoinColumn(name="memberId")
    List<Role> roles = new ArrayList<Role>();
    
	@Builder
	public Member(Long id, String loginId , String password, String nickname, String email) {
		
		this.id=id;
		this.loginId=loginId;
		this.password=password;
		this.nickname=nickname;
		this.email=email;
	}

}
