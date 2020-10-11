package com.nashtech.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringInAdvanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringInAdvanceApplication.class, args);
	}

	@Bean
	FilterRegistrationBean<ShallowEtagHeaderFilter> filterFilterRegistrationBean() {
		FilterRegistrationBean<ShallowEtagHeaderFilter>  bean = new FilterRegistrationBean<>(
				new ShallowEtagHeaderFilter()
		);
		bean.addUrlPatterns("/category/*");
		bean.setName("etagsFilter");
		return bean;
	}

}
