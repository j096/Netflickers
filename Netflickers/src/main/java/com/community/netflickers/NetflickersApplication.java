package com.community.netflickers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NetflickersApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflickersApplication.class, args);
	}

}
