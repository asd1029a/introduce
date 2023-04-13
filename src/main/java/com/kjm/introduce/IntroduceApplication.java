package com.kjm.introduce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class IntroduceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntroduceApplication.class, args);
	}

}
