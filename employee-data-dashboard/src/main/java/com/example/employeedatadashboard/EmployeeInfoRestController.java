package com.example.employeedatadashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

	@RefreshScope
	//@RestController
	@Controller
	public class EmployeeInfoRestController {
	    
		/*@Autowired
	    private RestTemplate restTemplate;
		*/
		
		@Autowired
		private EmployeeServiceProxy employeeServiceProxy; 
	    
	    
		@Autowired
		private ManagerServiceProxy managerServiceProxy; 

		
		
		
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
		
		
		
	    @HystrixCommand(fallbackMethod = "reliablefindmeFeign")
	    @RequestMapping(value = "/dashboard-feign/{id}")
	    public String findmeFeign(Map<String, Object> model,@PathVariable Long id, @RequestHeader("Authorization") String bearerToken) {
	    	System.out.println("Inside findmeFeign");
	    	EmployeeInfo emp = employeeServiceProxy.getEmployeeData(id, bearerToken);
	    	model.put("empDetails", emp);
	        return "empDetails";
	    }  
	    
	    public String reliablefindmeFeign(Map<String, Object> model,Long id,String bearerToken) {
	    	EmployeeInfo emp = new EmployeeInfo(1000,"Not Found", "Not Found", 5000, "9999");
	    	model.put("empDetails", emp);
	    	return "empDetails";
	    }
	    
	    
	    
	    @HystrixCommand(fallbackMethod = "reliablefindmanagerFeign")
	    @RequestMapping(value = "/dashboard-feign/manager/{manager}")
	    public String findmanagerFeign(Map<String, Object> model,@PathVariable Long manager,@RequestHeader("Authorization") String bearerToken) {
	    	List<Employee> empList = managerServiceProxy.getManagerData(manager,bearerToken);
	    	model.put("empDetails", empList);
	        return "empHierarchy";
	    }
	    
	    public String reliablefindmanagerFeign(Map<String, Object> model,Long manager,String bearerToken) {
	    	
	    	List<Employee> empList = new ArrayList<Employee>();
	    	
	    	empList.add(new Employee("Not Found", "Not Found", 5000));
	    	empList.add(new Employee("Not Found", "Not Found", 5000));	    	
	    	
	    	model.put("empDetails", empList);
	    	return "empHierarchy";
	    }
	    
	    
	    
	}
