package com.community.netflickers.entity.auditing;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.community.netflickers.entity.Member;

@Component
public class LoginUserAuditorAware  implements AuditorAware<String> {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
    @Override
    public Optional<String> getCurrentAuditor() {
    	
    	
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null == authentication || !authentication.isAuthenticated()) {
            return null;
        }
        
        
        Object principal =  authentication.getPrincipal();
        
        if(principal.equals("anonymousUser"))
        	return null;
        
        UserDetails userDetails = (UserDetails)principal; 
        
        return Optional.of(userDetails.getUsername());
    }
}
