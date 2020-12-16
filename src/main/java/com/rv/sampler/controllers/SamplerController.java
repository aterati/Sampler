/**
 * 
 */
package com.rv.sampler.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rv.sampler.model.Employee;
import com.rv.sampler.model.EmployeeRepository;

/**
 * Sampler Rest Endpoint Controller
 * 
 * defines endpoints for various persistence 
 * operations on employee entity
 * 
 * 
 * @author aterati
 *
 */
@RestController
@RequestMapping("/sampler")
public class SamplerController {

	@Autowired
	EmployeeRepository repo;
	
	
	@GetMapping("/employees")
	public List<Employee> listEmployees() {
		return repo.findAll();
	}

	@GetMapping("/employee/{id}")
	public Optional<Employee> findEmployee(@PathVariable Long id) {
		return repo.findById(id);
	}

	@PostMapping("/addEmployee")
	public Employee addEmployee(@RequestBody Employee emp) {
		return repo.save(emp);
	}

	@PutMapping("/updateEmployee/{id}")
	public Employee updateEmployee(@RequestBody Employee emp, @PathVariable Long id) {
		Optional<Employee> current_emp = repo.findById(id);

		return current_emp.map(employee -> {

			employee.setName(emp.getName());
			employee.setRole(emp.getRole());
			return repo.save(employee);
		}).orElseGet(() -> {
			emp.setId(id);
			return repo.save(emp);
		});

	}
	
	@DeleteMapping("deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		 repo.deleteById(id);
	}

}
