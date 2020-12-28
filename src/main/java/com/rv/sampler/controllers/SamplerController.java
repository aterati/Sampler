/**
 * 
 */
package com.rv.sampler.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rv.sampler.exception.EmployeeNotFoundException;
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
	public CollectionModel<EntityModel<Employee>> listEmployees() {
		
		List<EntityModel<Employee>> empList =  repo.findAll().stream().map(employee -> EntityModel.of(employee,
					linkTo(methodOn(SamplerController.class).findEmployee(employee.getId())).withSelfRel(),
					linkTo(methodOn(SamplerController.class).listEmployees()).withRel("employees"))).collect(Collectors.toList());
		
		return CollectionModel.of(empList, linkTo(methodOn(SamplerController.class).listEmployees()).withSelfRel());
	}

	@GetMapping("/employee/{id}")
	public EntityModel<Employee> findEmployee(@PathVariable Long id) {
		
		Employee em = repo.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
		
		 return EntityModel.of(em, linkTo(methodOn(SamplerController.class).findEmployee(em.getId())).withSelfRel(),
				 	linkTo(methodOn(SamplerController.class).listEmployees()).withRel("employees")); 
		
		
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
