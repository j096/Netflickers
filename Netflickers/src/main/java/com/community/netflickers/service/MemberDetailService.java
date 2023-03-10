package com.community.netflickers.service;

import java.util.NoSuchElementException;
import java.util.Optional;

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
	public int signup(MemberDto dto) {
		Long exists = memRepo.existsByLoginId(dto.getLoginId());
		if(exists > 0)
			return -1;
		
		String email = memRepo.findLoginIdByEmail(dto.getEmail());
		
		if(email != null)
			return -2;
		
		dto.setPassword(encoder.encode(dto.getPassword()));
		Member member = dto.toEntity();
		
		memRepo.save(member);
		return 1;
	}

	@Transactional
	public String findloginIdByEmail(String email) {
		
		String loginId = memRepo.findLoginIdByEmail(email);
		return loginId;
	}

	@Transactional
	public boolean chagePassword(MemberDto dto){
		
		Optional<Member> find = memRepo.findByLoginIdAndEmail(dto.getLoginId(),dto.getEmail());
		Member member;
		try {
			member = find.orElseThrow();
		}catch(NoSuchElementException e){
			logger.error(e.toString());
			return false;
		}
		member.setPassword(encoder.encode(dto.getPassword()));
		return true;
	}

}
