/**
 * 
 */
package com.rv.sampler.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Employee Repository 
 * 		extends JPA repository and provides all the basic persistance methods
 * 		to work with Employee entity
 * 
 * @param Employee: employee entity
 * @param Long: passing Id, primary key of employee entity
 * 
 * @author aterati
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
