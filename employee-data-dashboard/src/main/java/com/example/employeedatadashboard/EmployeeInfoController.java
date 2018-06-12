package com.example.employeedatadashboard;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

	@Controller
	public class EmployeeInfoController {
	    

		
		@RequestMapping("/index")
		public String welcome(Map<String, Object> model) {
			//model.put("message", "Welcome Shirish");
			return "index";
		}
		
		 @HystrixCommand(fallbackMethod = "reliablefindmeFeign")
	    @RequestMapping(value = "/dashboard-feign/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public Resource<EmployeeInfo> findmeFeign(Map<String, Object> model,@PathVariable Long id, @RequestHeader("Authorization") String bearerToken) {
	    	System.out.println("Inside findmeFeign");
	    	EmployeeInfo emp = employeeServiceProxy.getEmployeeData(id, bearerToken);
	    	Resource<EmployeeInfo> resource = new Resource<EmployeeInfo>(emp);
	    	ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findPeersFeign(bearerToken));
	    	resource.add(linkTo.withRel("All-Employees"));
		model.put("empDetails", resource);    
	        return "empDetails";
	    }  
		
	    
	    
	}
