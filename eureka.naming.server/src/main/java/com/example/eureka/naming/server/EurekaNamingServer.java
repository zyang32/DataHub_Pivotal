package com.example.eureka.naming.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaNamingServer {

	public static void main(String[] args) {
		System.out.println("Inside EurekaNamingServer 1");
		SpringApplication.run(EurekaNamingServer.class, args);
	}
}
