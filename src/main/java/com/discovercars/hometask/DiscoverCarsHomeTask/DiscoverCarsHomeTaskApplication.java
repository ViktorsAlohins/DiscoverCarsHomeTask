package com.discovercars.hometask.DiscoverCarsHomeTask;

import com.discovercars.hometask.DiscoverCarsHomeTask.filter.AccessLogFilter;
import com.discovercars.hometask.DiscoverCarsHomeTask.repository.AccessLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.discovercars.hometask.DiscoverCarsHomeTask.repository")
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

	@Autowired
	private AccessLogRepository accessLogRepository;

	@Bean
	public FilterRegistrationBean<AccessLogFilter> loggingFilter() {
		FilterRegistrationBean<AccessLogFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AccessLogFilter(accessLogRepository));
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}