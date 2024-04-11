package com.revature.HelloSpringMVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.revature")
public class HelloSpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringMvcApplication.class, args);
	}

}
