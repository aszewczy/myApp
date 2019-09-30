package pl.szewczyk.test.myApp;

import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAppApplication.class, args);
	}

	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() { //definiuje, któe endpointy będą filtrowane 
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(); //tworzymy obiekt filtra
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.setUrlPatterns(Collections.singleton("/api/hello/*")); //co ma przechodzić przez ten filtr
		return filterRegistrationBean;
		
	}
	
}
