package com.example.employeedatadashboard;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

	@RefreshScope
	@RestController
	public class EmployeeInfoController {
	    
		@Autowired
	    private RestTemplate restTemplate;
		
		@Autowired
		private EmployeeServiceProxy employeeServiceProxy; 
	    
	    
		@Autowired
		private ManagerServiceProxy managerServiceProxy; 

		
		@RequestMapping(value = "/dashboard/hello")
	    public String findHello() {
	        return "Hello";
	    }
		
		@RequestMapping(value = "/dashboard/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public Resource<EmployeeInfo> findme(@PathVariable Long id, @RequestHeader("Authorization") String bearerToken) {
	    	String url = "https://employee-service-dev.cfapps.io/employee/find/" + id;
	        HttpHeaders requestHeaders = new HttpHeaders();
	        requestHeaders.add("Authorization", bearerToken);
	        HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
	        System.out.println("requestHeaders:" + requestHeaders.toString());
	        ResponseEntity<EmployeeInfo> emp = restTemplate.exchange(url, HttpMethod.GET, httpEntity, EmployeeInfo.class);
	        System.out.println("EmployeeInfo Body: " +   emp.toString());
	        Resource<EmployeeInfo> resource = new Resource<EmployeeInfo>(emp.getBody());
	        ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findPeers(bearerToken));
	    	resource.add(linkTo.withRel("All-Employees"));
	        return resource;
	   }
	    
	   @RequestMapping(value = "/dashboard/peers", produces = MediaType.APPLICATION_JSON_VALUE)
	   public ResponseEntity<Collection> findPeers(@RequestHeader("Authorization") String bearerToken) {
	    	String url = "https://employee-service-dev.cfapps.io/employee/findall";
	        HttpHeaders requestHeaders = new HttpHeaders();
	        requestHeaders.add("Authorization", bearerToken);
	        HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
	        ResponseEntity<Collection> empList = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Collection.class);
	        return empList;
	   }
	    
	    
	    @HystrixCommand(fallbackMethod = "reliablefindmeFeign")
	    @RequestMapping(value = "/dashboard-feign/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public Resource<EmployeeInfo> findmeFeign(@PathVariable Long id, @RequestHeader("Authorization") String bearerToken) {
	    	EmployeeInfo emp = employeeServiceProxy.getEmployeeData(id, bearerToken);
	    	Resource<EmployeeInfo> resource = new Resource<EmployeeInfo>(emp);
	    	ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findPeersFeign(bearerToken));
	    	resource.add(linkTo.withRel("All-Employees"));
	        return resource;
	    }  
	    
	    public Resource<EmployeeInfo> reliablefindmeFeign(Long id,String bearerToken) {
	    	EmployeeInfo emp = new EmployeeInfo(1000,"Not Found", "Not Found", 5000, "9999");
	    	Resource<EmployeeInfo> resource = new Resource<EmployeeInfo>(emp);
	        return resource;
	    }
	    
	    
	    //@HystrixCommand(fallbackMethod = "reliablefindmanagerFeign")
	    @RequestMapping(value = "/dashboard-feign/manager/{manager}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public List<Employee> findmanagerFeign(@PathVariable Long manager,@RequestHeader("Authorization") String bearerToken) {
	    	List<Employee> emp = managerServiceProxy.getManagerData(manager,bearerToken);
	        return emp;
	    }
	    
	    /*public String reliablefindmanagerFeign(Long manager) {
	    	return "Deafult Response For - findmanagerFeign";
	    }*/
	    
	    
	    //@HystrixCommand(fallbackMethod = "reliablefindPeersFeign")
	    @RequestMapping(value = "/dashboard-feign/peers", produces = MediaType.APPLICATION_JSON_VALUE)
	    public Collection <Employee> findPeersFeign( @RequestHeader("Authorization") String bearerToken) {
	    	Collection <Employee> list = employeeServiceProxy.getAllEmployeeData(bearerToken);
	        return list;
	    }
	    
	    /*public String reliablefindPeersFeign() {
	    	return "Deafult Response For - reliablefindPeersFeign";
	    }*/
	}
