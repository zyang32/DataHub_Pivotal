package com.example.managerdataservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.managerdataservice.entity.Employee;
import com.example.managerdataservice.service.EmployeeRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class EmployeeSearchController {
		@Autowired
		EmployeeRepository employeeRepository;
		
		/*@Autowired
		private Environment env;
		*/
		
		@Autowired
		private EurekaClient discoveryClient;


		public String getServiceInstanceInformation() {
			InstanceInfo instance = discoveryClient.getNextServerFromEureka("MANAGER-SERVICE", false);
			String instanceInformation = instance.getHostName() + "---" + instance.getInstanceId() + "---" + instance.getIPAddr();
			return instanceInformation;
		}	
			   
		@RequestMapping("/manager/find/{manager}")
		public List<Employee> findByManager(@PathVariable Long manager,@RequestHeader("Authorization") String bearerToken){
			List <Employee> emp = employeeRepository.findByManager(manager);
			return emp;
		}
	   
}
