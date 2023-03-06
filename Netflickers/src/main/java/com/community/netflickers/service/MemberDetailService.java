package com.community.netflickers.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.community.netflickers.entity.Member;
import com.community.netflickers.repository.MemberRepository;
import com.community.netflickers.service.dto.MemberDto;

@Service
public class MemberDetailService implements UserDetailsService{
	
	@Autowired
	MemberRepository memRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		Member member = memRepo.findByLoginId(userId);
		
		if(member == null) {
			throw new UsernameNotFoundException("계정을 찾을 수 없습니다.");
		}
		
		return new MemberDetail(member);
	}

	@Transactional
	public boolean signup(MemberDto dto) {
		Long exists = memRepo.existsByLoginId(dto.getLoginId());
		
		if(exists > 0)
			return false;
		
		dto.setPassword(encoder.encode(dto.getPassword()));
		Member member = dto.toEntity();
		
		memRepo.save(member);
		return true;
	}

	public boolean checkMember(MemberDto dto) {
		UserDetails member = loadUserByUsername(dto.getLoginId());
		
		if(encoder.matches(dto.getPassword(), member.getPassword()))
				return true;
		
		return false;
	}

	public String findloginIdByEmail(String email) {
		
		String loginId = memRepo.findLoginIdByEmail(email);
		return loginId;
	}

}
