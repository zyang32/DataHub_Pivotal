package com.example.employeedataservice.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeedataservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
