package com.ais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class AutomaticIrrigationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomaticIrrigationSystemApplication.class, args);
	}

}
