package com.ahasan.item;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
@EnableEurekaClient
@Log
public class ItemApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ItemApplicationRunner.class, args);
		log.info("Product service running....!");
	}

}
