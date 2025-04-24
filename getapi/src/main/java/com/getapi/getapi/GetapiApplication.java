package com.getapi.getapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class GetapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetapiApplication.class, args);
	}

}
