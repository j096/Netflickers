package com.community.netflickers;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.community.netflickers.entity.Member;

@SpringBootApplication
@EnableJpaAuditing
public class NetflickersApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflickersApplication.class, args);
	}

}
