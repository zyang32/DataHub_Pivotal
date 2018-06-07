package com.example.managerdataservice.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.managerdataservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
	List<Employee> findByManager(long manager);

}
