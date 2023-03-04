package com.community.netflickers.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.community.netflickers.entity.Member;
import com.community.netflickers.entity.Role;

public class MemberDetail implements UserDetails{
	
	private Member member;
	
	public MemberDetail(Member member) {
		this.member = member;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
//        ArrayList<GrantedAuthority> auths = new ArrayList<>();
//        for(Role role : member.getRoles()){
//            auths.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return role.getRoleName();
//                }
//            });
//        }
//        return auths;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return member.getLoginId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
