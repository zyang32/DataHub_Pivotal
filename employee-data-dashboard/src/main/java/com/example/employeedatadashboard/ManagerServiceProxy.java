package com.example.employeedatadashboard;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="zuul-service")
@RibbonClient(name="manager-service")
public interface ManagerServiceProxy {
	@GetMapping("/manager/find/{manager}" )
	public List <Employee> getManagerData(@PathVariable("manager") Long manager, @RequestHeader("Authorization") String bearerToken);
}
