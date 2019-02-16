package com.example.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class})
@EnableConfigurationProperties(WeatherAppProperties.class)
@EnableCaching(proxyTargetClass = true)
public class WeatherApp {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApp.class, args);
	}

}
