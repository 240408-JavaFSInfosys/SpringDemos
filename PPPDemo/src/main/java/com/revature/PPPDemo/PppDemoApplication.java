package com.revature.PPPDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.revature.models") //This is telling Spring to look for DB entities here
public class PppDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PppDemoApplication.class, args);
	}

}
