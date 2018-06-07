package com.example.employeedataservice;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeedataservice.entity.Employee;
import com.example.employeedataservice.entity.EmployeeInfo;
import com.example.employeedataservice.service.EmployeeRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class EmployeeSearchController {
		@Autowired
		EmployeeRepository employeeRepository;
		
		//@Autowired
		//private Environment env;

		@Autowired
		private EurekaClient discoveryClient;

		public String getServiceInstanceInformation() {
		    InstanceInfo instance = discoveryClient.getNextServerFromEureka("EMPLOYEE-SERVICE", false);
		    String instanceInformation = instance.getHostName() + "---" + instance.getInstanceId() + "---" + instance.getIPAddr();
		    return instanceInformation;
		}

	   
	   @RequestMapping("/employee/find/{id}")
	   public EmployeeInfo findById(@PathVariable Long id, @RequestHeader("Authorization") String bearerToken){
		  //System.out.println(" bearerToken :" + bearerToken);
		  Optional <Employee> emp = employeeRepository.findById(id);
		  EmployeeInfo empInfo = new EmployeeInfo(emp.get().getId(),emp.get().getName(),emp.get().getRole(),emp.get().getManager(),getServiceInstanceInformation());
		  //System.out.println("EmployeeInfo: " +  empInfo.toString());
	      return empInfo;
	   }
	   
	   
	   @RequestMapping("/employee/findall")
	   public Collection<Employee> findAll(@RequestHeader("Authorization") String bearerToken){
	      return employeeRepository.findAll();
	   }

}
