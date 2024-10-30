package com.example.bookmark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookmarkApplication {

	public static void main(String[] args) {

		SpringApplication.run(BookmarkApplication.class, args);
	}

}
