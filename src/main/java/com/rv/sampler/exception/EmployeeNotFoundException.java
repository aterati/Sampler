/**
 * 
 */
package com.rv.sampler.exception;

/**
 * @author aterati
 *
 */
@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {
	
	public EmployeeNotFoundException(Long id){
		super("Employee not found with id: "+ id);
	}

}
