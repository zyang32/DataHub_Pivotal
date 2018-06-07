package com.example.employeeauthserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EmployeeAuthServerConfiguration extends GlobalAuthenticationConfigurerAdapter{


	// Initialize user credentials - hardcoded for simplicity - JPA and DB can be used for production
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		 BCryptPasswordEncoder encoder = passwordEncoder();
		
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("user1").password(encoder.encode("password1")).roles("USER").and().
		withUser("user2").password(encoder.encode("password2")).roles("ADMIN");
	}
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
