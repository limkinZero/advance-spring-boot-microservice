package com.ahasan.sales;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class SalesApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SalesApplicationRunner.class, args);
		log.info("Sales service started...!!!");
	}

}
