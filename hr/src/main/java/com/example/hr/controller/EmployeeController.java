package com.example.hr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.entity.Employee;
import com.example.hr.service.EmployeeService;

@RestController
@RequestScope
@RequestMapping("employees")
@CrossOrigin
public class EmployeeController {
	@Autowired
	private EmployeeService employeeSrv;

	@GetMapping("{identity}")
	public Employee getEmployeeByIdentity(@PathVariable String identity) {
		return employeeSrv.findEmployeeByIdentity(identity);
	}

	// http://localhost:7001/hr/api/v1/employees?page=10&size=25
	@GetMapping(params = { "page", "size" })
	public List<Employee> getAllEmployees(@RequestParam int page, @RequestParam int size) {
		return employeeSrv.findAllEmployee(page, size);
	}

	@PostMapping
	public Employee createEmployee(@RequestBody Employee emp) {
		return employeeSrv.addEmployee(emp);
	}

	@PutMapping
	public Employee updateEmployee(@RequestBody Employee emp) {
		return employeeSrv.updateEmployee(emp);
	}

	@DeleteMapping("{identity}")
	public Employee removeEmployeeByIdentity(@PathVariable String identity) {
		return employeeSrv.removeEmployeeByIdentity(identity);
	}
}
