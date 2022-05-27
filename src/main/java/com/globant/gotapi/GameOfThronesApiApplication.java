package com.globant.gotapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GameOfThronesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameOfThronesApiApplication.class, args);
	}

}
