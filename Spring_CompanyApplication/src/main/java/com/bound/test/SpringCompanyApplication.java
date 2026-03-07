package com.bound.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCompanyApplication.class, args);
	}

}
