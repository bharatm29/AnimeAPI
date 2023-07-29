package com.bharat.AnimeAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AnimeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnimeApiApplication.class, args);
	}

	@Bean
	public RestTemplate generateRestTemplate(){
		return new RestTemplate();
	}
}