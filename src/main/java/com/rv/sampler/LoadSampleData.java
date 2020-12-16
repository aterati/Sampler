/**
 * 
 */
package com.rv.sampler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.rv.sampler.model.Employee;
import com.rv.sampler.model.EmployeeRepository;

/**
 * @author aterati
 *
 */
@Component
public class LoadSampleData{

	
//	Logger
	
//	@Override
//	public void run(String... args) throws Exception {
//
//		repo.save(new Employee("IronMan", "Suit"));
//		repo.save(new Employee("Thor", "Hammer"));
//		repo.save(new Employee("Hulk", "Green"));
//		
//	}
//	
	@Bean
	CommandLineRunner loadData(EmployeeRepository repo) {
		
		return args -> {repo.save(new Employee("IronMan", "Suit"));
		repo.save(new Employee("Thor", "Hammer"));
		repo.save(new Employee("Hulk", "Green"));
		};
	}
}
