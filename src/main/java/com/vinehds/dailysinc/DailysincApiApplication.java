package com.vinehds.dailysinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DailysincApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DailysincApiApplication.class, args);
	}

}
