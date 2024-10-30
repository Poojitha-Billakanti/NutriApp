package com.example.fetch.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Configuration
@EnableFeignClients

public class FetchApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(FetchApiApplication.class, args);
	}
	@Bean
	public RestTemplate getTemplate(){
		return new RestTemplate();
	}

}
