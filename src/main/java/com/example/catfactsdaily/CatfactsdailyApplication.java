package com.example.catfactsdaily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CatfactsdailyApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatfactsdailyApplication.class, args);
	}

}
