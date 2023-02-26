package com.community.netflickers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.community.netflickers.service.MemberDetail;
import com.community.netflickers.service.MemberDetailService;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        http
            .authorizeHttpRequests((authz) -> {
				try {
					authz
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers("/signup").permitAll()
						.requestMatchers("/member/**").permitAll()
						.requestMatchers("/post/list").permitAll()
						.requestMatchers("/js/**").permitAll()
						.requestMatchers("/css/**").permitAll()
						.and()
						.formLogin()
						.loginPage("/login")
						.loginProcessingUrl("/member/login")
		                .defaultSuccessUrl("/",true)
		                .permitAll()
		                .and()
		                .logout()
		                .logoutUrl("/member/logout")
		                .logoutSuccessUrl("/login")
		                .permitAll();
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
            ).httpBasic();
        return http.build();
    }
    
    @Bean
    public BCryptPasswordEncoder encodePassword() {  // 회원가입 시 비밀번호 암호화에 사용할 Encoder 빈 등록
        return new BCryptPasswordEncoder();
    }
    
    

}
