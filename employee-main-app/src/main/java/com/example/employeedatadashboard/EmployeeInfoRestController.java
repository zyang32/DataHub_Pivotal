package com.example.employeedatadashboard;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

	@Controller
	public class EmployeeInfoRestController {
	    
		@Autowired
	    private RestTemplate restTemplate;
		
		
		
		/*@RequestMapping(value = "/dashboard/hello")
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
	   
	 

	   */
	    
		@RequestMapping("/index")
		public String welcome(Map<String, Object> model) {
			//model.put("message", "Welcome Shirish");
			return "index";
		}
		
		
		@RequestMapping("/empDetails/{id}")
		public String getEmployeeDetails(Map<String, Object> model,@PathVariable Long id, @RequestHeader("Authorization") String bearerToken) {
			String url = "https://zuul-service-dev.cfapps.io/dashboard-feign/" + id;
	        HttpHeaders requestHeaders = new HttpHeaders();
	        requestHeaders.add("Authorization", bearerToken);
	        HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
	        System.out.println("requestHeaders:" + requestHeaders.toString());
	        ResponseEntity<EmployeeInfo> emp = restTemplate.exchange(url, HttpMethod.GET, httpEntity, EmployeeInfo.class);
	        model.put("employee", emp.getBody());
	        return "empDetails";
		}
		
		
		///dashboard-feign/manager/{manager}
		@RequestMapping("/empHierarchy/{id}")
		public String getEmployeeHierarchy(Map<String, Object> model,@PathVariable Long id, @RequestHeader("Authorization") String bearerToken) {
			String url = "https://zuul-service-dev.cfapps.io/dashboard-feign/manager/" + id;
	        HttpHeaders requestHeaders = new HttpHeaders();
	        requestHeaders.add("Authorization", bearerToken);
	        HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);
	        System.out.println("requestHeaders:" + requestHeaders.toString());
	        ResponseEntity<Collection> emp = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Collection.class);
	        model.put("employeeList", emp.getBody());
	        return "empHierarchy";
		}
	    
	    
	}
