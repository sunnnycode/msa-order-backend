package com.shortpingoo.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BackEndOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndOrderApplication.class, args);
	}

}
