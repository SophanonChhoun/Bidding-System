package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("===========Application is starting===========");
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("===========Application started successfully===========");
	}

}
