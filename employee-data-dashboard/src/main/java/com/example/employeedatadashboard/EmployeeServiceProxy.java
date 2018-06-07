package com.example.employeedatadashboard;

import java.util.Collection;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

// ZUUL URI - http://localhost:8765/{app-name}/uri
// Case 1 - URI to call employee-data-dashboard via zuul gateway - http://localhost:8765/employee-data-dashboard/dashboard-feign/peers
// Case 2 - URI to call employee-data-service via zuul gateway - http://localhost:8765/employee-data-service/employee/findall

// This setting is for Feign client to call service "employee-data-service" (using Eureka) without Zuul
//@FeignClient(name="employee-data-service")

//This setting is for Feign client to call service "employee-data-service" (using Eureka) with Zuul
@FeignClient(name="zuul-service")
// Client side load balancing using Ribbon and Eureka (service Id read from Eureka) - Client service is "employee-data-dashboard"
// Service "employee-data-dashboard" calls service "employee-data-service" using Eureka and Ribbon
@RibbonClient(name="employee-service")
public interface EmployeeServiceProxy {
	// @GetMapping("/employee/find/{id}") // Prefixing service name as defined in Eureka is NOT required when zuul gateway is NOT used 
	@GetMapping("/employee/find/{id}") // Prefixing service name as defined in Eureka is required when zuul gateway is used 
	public EmployeeInfo getEmployeeData(@PathVariable("id") Long id, @RequestHeader("Authorization") String bearerToken);
	
	@GetMapping("/employee/findall")
	public Collection <Employee> getAllEmployeeData( @RequestHeader("Authorization") String bearerToken);
}
