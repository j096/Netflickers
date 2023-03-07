package com.community.netflickers.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/*https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter*/
@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private SimpleUrlAuthenticationFailureHandler loginFailureHandler;
	
	// permitUrl 순서 중요 (리퀘스트 흐름상 먼저 오는 것이 먼저 기술되어야 한다.)
	private static String[] permitUrl = {"/signup","/id-find","/member/**","/post/list","/post/read/**","/comment/list/**"};
	private static String[] authUrl = {"/post/write/**","/post/save/**","/post/update/**","/post/delete/**"
			,"/comment/save","/comment/update/**","/comment/delete/**"};
	private static String[] ignoreUrl = {"/h2-console/**","/js/**","/css/**","/favicon.ico"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        http
            .authorizeHttpRequests((authz) -> {
				try {
					authz
						.requestMatchers(permitUrl).permitAll()
						.requestMatchers(authUrl).authenticated()
						.and()
						.formLogin()
						.loginPage("/login")
						.loginProcessingUrl("/login")
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
    public WebSecurityCustomizer webSecurityCustomizer() {
    		//정적파일 및 테스트 db 콘솔 무시
        return (web) -> web.ignoring().requestMatchers(ignoreUrl);
    }
    
    @Bean
    public PasswordEncoder encoder() {  // 회원가입 및 로그인 시 비밀번호 암호화에 사용할 Encoder 빈 등록
        return new BCryptPasswordEncoder();
    }
    
    

}
