package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.EmployeeHandeller.EmployeeNotFoundException;
import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;

	/*
	 * @Autowired public EmployeeRestController(EmployeeDAO employeeDAO) {
	 * this.employeeDAO=employeeDAO; }
	 */
	@GetMapping("/employees")
	public List<Employee> getEmployees() {

		return employeeService.findAll();
	}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {

		Employee employee = employeeService.findById(employeeId);
		if (employee == null)
			throw new EmployeeNotFoundException("this employees with id = " + employeeId + " not found");
		return employee;
	}

	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee employee) {

		employee.setId(0);
		employeeService.save(employee);
		return employee;
	}

	@PutMapping("/employees")
	public Employee updatEmployee(@RequestBody Employee employee) {

		employeeService.save(employee);
		return employee;
	}

	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {

		Employee employee = employeeService.findById(employeeId);
		if (employee == null)
			throw new EmployeeNotFoundException("this employees with id = " + employeeId + " not found");

		employeeService.deleteById(employeeId);
		return "the employee with id { " + employeeId + " } is deleted ";
	}

}
