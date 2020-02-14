package com.example.hr.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

@Document(collection = "employees")
public class Employee {
	@Id
	private String id;
	@TcKimlikNo(message = "")
	private String identityNo;
	@Size(min = 3)
	private String fullname;
	@Min(2300)
	private double salary;
	@Iban
	private String iban;
	@Min(1960)
	private int birthYear;
	private String photo;
	private boolean fulltime;
	private Department department;

	public Employee() {
	}

	public Employee(String identityNo, @Size(min = 3) String fullname, @Min(2300) double salary, String iban,
			@Min(1960) int birthYear, boolean fulltime, Department department) {
		this.identityNo = identityNo;
		this.fullname = fullname;
		this.salary = salary;
		this.iban = iban;
		this.birthYear = birthYear;
		this.fulltime = fulltime;
		this.department = department;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public boolean isFulltime() {
		return fulltime;
	}

	public void setFulltime(boolean fulltime) {
		this.fulltime = fulltime;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", identityNo=" + identityNo + ", fullname=" + fullname + ", salary=" + salary
				+ ", iban=" + iban + ", birthYear=" + birthYear + ", fulltime=" + fulltime + ", department="
				+ department + "]";
	}

}
