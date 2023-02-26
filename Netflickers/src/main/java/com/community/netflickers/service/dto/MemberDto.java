package com.community.netflickers.service.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.community.netflickers.entity.Member;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto extends BaseTimeDto{
	
    private long id;
    
    private String loginId; // 로그인시 받는 id값
    
    private String password; // 로그인시 받는 password값
    
    private String nickname;
    
    private String createdDate;
    
    private String modifiedDate;
    
    private String email;
    
    @Autowired
    private static PasswordEncoder encoder = new BCryptPasswordEncoder();
    
    @Builder
    public MemberDto(Member member) {
		
		this.id = member.getId();
		
		this.loginId = member.getLoginId();
		
		this.password = member.getPassword();
		
		this.nickname = member.getNickname();
		
		this.email = member.getEmail();
		
		this.createdDate = toHhMmSsFormat(member.getCreatedDate());
		
		this.modifiedDate = toHhMmSsFormat(member.getModifiedDate());
	}
	
	public Member toEntity() {
		return Member.builder()
				.id(id)
				.loginId(loginId)
				.password(password)
				.nickname(nickname)
				.email(email)
				.build();
				
	}
	
	public void encrytpPassword() {
		this.password = encoder.encode(this.password);
	}

}
