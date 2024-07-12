package com.xavelo.piapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:git.properties")
public class PiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiApiApplication.class, args);
	}

}
