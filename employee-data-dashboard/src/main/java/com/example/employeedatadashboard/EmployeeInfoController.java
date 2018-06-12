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
		
	    
	    
	}
