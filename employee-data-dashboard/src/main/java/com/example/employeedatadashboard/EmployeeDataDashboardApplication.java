package com.example.employeedatadashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableFeignClients("com.example.employeedatadashboard")
@EnableResourceServer
@RestController
public class EmployeeDataDashboardApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeDataDashboardApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(EmployeeDataDashboardApplication.class);
	}
	
	@Bean
	 public RestTemplate restTemplate(RestTemplateBuilder builder) {
	      return builder.build();
	 }
	
	@Bean
	public ViewResolver getViewResolver(){
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/jsp/");
	    resolver.setSuffix(".jsp");
	    resolver.setViewClass(JstlView.class);
	    return resolver;
	}
	 
	 
	 /*@Bean
	 public RequestInterceptor requestTokenBearerInterceptor() {
	     return new RequestInterceptor() {
	         @Override
	         public void apply(RequestTemplate requestTemplate) {
	             OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)
	                     SecurityContextHolder.getContext().getAuthentication().getDetails();

	             requestTemplate.header("Authorization", "bearer " + details.getTokenValue());
	         }
	     };
	 }*/
}
