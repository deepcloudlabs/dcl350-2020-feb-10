package com.example.hr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hr.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findOneByIdentityNo(String identity);
	Employee findTopByOrderBySalaryDesc();
	@Query(value = "select e from Employees e where e.salary between :from and :to",nativeQuery = true)
	List<Employee> findBySalaryBetween(double from,double to);
}
