package com.example.hr.controller;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
import com.example.validation.TcKimlikNo;

@RestController
@RequestScope
@RequestMapping("employees")
@CrossOrigin
@Validated
public class EmployeeController {
	@Autowired
	private EmployeeService employeeSrv;

	@GetMapping("{identity}")
	public Employee getEmployeeByIdentity(@TcKimlikNo @PathVariable String identity) {
		return employeeSrv.findEmployeeByIdentity(identity);
	}

	// http://localhost:7001/hr/api/v1/employees?page=10&size=25
	@GetMapping(params = { "page", "size" })
	public List<Employee> getAllEmployees(@RequestParam @Min(0) int page, @RequestParam @Min(10) @Max(100) int size) {
		return employeeSrv.findAllEmployee(page, size);
	}

	@PostMapping
	public Employee createEmployee(@RequestBody @Validated Employee emp) {
		return employeeSrv.addEmployee(emp);
	}

	@PutMapping
	public Employee updateEmployee(@RequestBody @Validated Employee emp) {
		return employeeSrv.updateEmployee(emp);
	}

	@DeleteMapping("{identity}")
	public Employee removeEmployeeByIdentity(@TcKimlikNo @PathVariable String identity) {
		return employeeSrv.removeEmployeeByIdentity(identity);
	}
}
