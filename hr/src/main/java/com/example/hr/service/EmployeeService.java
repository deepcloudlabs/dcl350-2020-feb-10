package com.example.hr.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.entity.Employee;
import com.example.hr.repository.EmployeeRepository;
import com.example.hr.service.exception.BaseException;
import com.example.hr.service.exception.ErrorCode;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository empRepo;

	public Employee findEmployeeByIdentity(String identity) {
		return empRepo.findOneByIdentityNo(identity).orElseThrow(() -> new BaseException("Cannot find the employee",
				ErrorCode.EMPLOYEE_NOT_FOUND, "b8c54f20-fac3-423c-b611-a95227ec0249"));
	}

	public List<Employee> findAllEmployee(int page, int size) {
		return empRepo.findAll(PageRequest.of(page, size)).getContent();
	}

	@Transactional
	public Employee addEmployee(Employee emp) {
		emp.setId(null);
		String identity = emp.getIdentityNo();
		Optional<Employee> employee = empRepo.findOneByIdentityNo(identity);
		if (employee.isPresent())
			throw new BaseException("Employee already exists with the same identity no",
					ErrorCode.ALREADY_EXISTING_EMPLOYEE, "1cc40c5d-bb30-4e71-9ed3-1e5acab92151");
		return empRepo.save(emp);
	}

	@Transactional
	public Employee updateEmployee(Employee emp) {
		String identity = emp.getIdentityNo();
		Optional<Employee> employee = empRepo.findOneByIdentityNo(identity);
		if (!employee.isPresent())
			throw new BaseException("Cannot find the employee to update", ErrorCode.EMPLOYEE_NOT_FOUND,
					"55688710-17ef-4d5a-86aa-fd832935e0b2");
		Employee managedEmployee = employee.get();
		if (Objects.nonNull(emp.getPhoto()))
			managedEmployee.setPhoto(emp.getPhoto());
		if (Objects.nonNull(emp.getFullname()))
			managedEmployee.setFullname(emp.getFullname());
		if (Objects.nonNull(emp.getIban()))
			managedEmployee.setIban(emp.getIban());
		managedEmployee.setSalary(emp.getSalary());
		managedEmployee.setFulltime(emp.isFulltime());
		return managedEmployee;
	}

	@Transactional
	public Employee removeEmployeeByIdentity(String identity) {
		Optional<Employee> employee = empRepo.findOneByIdentityNo(identity);
		if (!employee.isPresent())
			throw new BaseException("Cannot find the employee to remove", ErrorCode.EMPLOYEE_NOT_FOUND,
					"768ad1f0-4848-410f-9958-29715b159348");
		Employee emp = employee.get();
		empRepo.delete(emp);
		return emp;
	}

}
