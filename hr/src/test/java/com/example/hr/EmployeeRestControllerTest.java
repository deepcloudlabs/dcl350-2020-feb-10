package com.example.hr;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.hr.entity.Department;
import com.example.hr.entity.Employee;
import com.example.hr.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = HrApplication.class)
@AutoConfigureMockMvc
class EmployeeRestControllerTest {
	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private EmployeeRepository empRepo;

	@Test
	void listTwoEmployees() throws Exception {
		Employee jack = new Employee("93016289514","Jack Bauer",
				400_000,"TR160006214856147784314666",
				1962,false,Department.IT);
		Employee kate = new Employee("68866499758","Kate Austen",
				500_000,"TR830006222968815128584673",
				1986,true,Department.HR);
		Pageable page = PageRequest.of(0, 10);
		Page<Employee> employees =Mockito.mock(Page.class);
		Mockito.when(employees.getContent())
		       .thenReturn(Arrays.asList(jack,kate));
		Mockito.when(empRepo.findAll(page))
		       .thenReturn(employees);
	}

	@Test
	void createEmployee_isOk() throws Exception {
		String identity = "99666377878";
		Employee emp = new Employee(
				identity,
				"Ben Linus",
				400_000,
				"TR160006214856147784314666",
				1973,
				true,
				Department.IT);
		Mockito.when(empRepo.findOneByIdentityNo(identity)).thenReturn(Optional.empty());
		Mockito.when(empRepo.save(emp)).thenReturn(emp);
		mvc.perform(post("/employees").content(mapper.writeValueAsString(emp)).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
