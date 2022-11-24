package com.example.cartfidential;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CartfidentialApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartfidentialApplication.class, args);
	}

}
