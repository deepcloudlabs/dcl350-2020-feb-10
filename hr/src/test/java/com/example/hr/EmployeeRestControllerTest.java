package com.example.hr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.hr.entity.Department;
import com.example.hr.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK,
classes = HrApplication.class)
@AutoConfigureMockMvc
class EmployeeRestControllerTest {
	@Autowired private MockMvc mvc;
	@Autowired private ObjectMapper mapper;
	@Test
	void createEmployee_isOk() throws Exception {
		Employee emp = new Employee();
		emp.setBirthYear(1973);
		emp.setFullname("Ben Linus");
		emp.setIdentityNo("99666377878");
		emp.setSalary(400_000);
		emp.setIban("TR160006214856147784314666");
		emp.setDepartment(Department.IT);
		emp.setFulltime(true);
		mvc.perform(post("/employees")
				    .content(mapper.writeValueAsString(emp))
				    .contentType(MediaType.APPLICATION_JSON)				    
			).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
