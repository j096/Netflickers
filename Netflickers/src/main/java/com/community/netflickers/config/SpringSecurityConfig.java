package com.community.netflickers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private SimpleUrlAuthenticationFailureHandler loginFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        http
            .authorizeHttpRequests((authz) -> {
				try {
					authz
//					.anyRequest().permitAll();
						.requestMatchers("/h2-console/**").permitAll()
						.requestMatchers("/signup").permitAll()
						.requestMatchers("/member/**").permitAll()
						.requestMatchers("/post/list").permitAll()
						.requestMatchers("/js/**").permitAll()
						.requestMatchers("/css/**").permitAll()
						.requestMatchers("/post/read/**").authenticated()
						.and()
						.formLogin()
						.loginPage("/login")
						.loginProcessingUrl("/member/login")
		                .defaultSuccessUrl("/post/list",true)
		                .failureHandler(loginFailureHandler)
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
    public PasswordEncoder encoder() {  // 회원가입 시 비밀번호 암호화에 사용할 Encoder 빈 등록
        return new BCryptPasswordEncoder();
    }
    
    

}
