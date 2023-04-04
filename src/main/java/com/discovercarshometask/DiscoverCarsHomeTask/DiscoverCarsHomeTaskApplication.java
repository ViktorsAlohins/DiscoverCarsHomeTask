package com.discovercarshometask.DiscoverCarsHomeTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DiscoverCarsHomeTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoverCarsHomeTaskApplication.class, args);
	}

}
