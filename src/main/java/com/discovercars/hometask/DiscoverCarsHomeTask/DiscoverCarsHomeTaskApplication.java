package com.discovercars.hometask.DiscoverCarsHomeTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DiscoverCarsHomeTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoverCarsHomeTaskApplication.class, args);
	}

	@Bean
	public CommonsRequestLoggingFilter requestLoggingFilter() {
		CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
		loggingFilter.setIncludeClientInfo(true);
		loggingFilter.setIncludeQueryString(true);
		loggingFilter.setIncludePayload(true);
		loggingFilter.setMaxPayloadLength(64000);
		loggingFilter.setIncludeHeaders(true);
		return loggingFilter;

	}
}