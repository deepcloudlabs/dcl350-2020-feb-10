package com.example.hr.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.hr.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

	Optional<Employee> findOneByIdentityNo(String identity);

	Employee findTopByOrderBySalaryDesc();

}
