/**
 * 
 */
package com.rv.sampler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author arvind
 *
 */
@ControllerAdvice
public class EmployeeNotFoundAdvisor {
	
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmployeeNotFoundException.class)
	String employeeNotFoundHandler(EmployeeNotFoundException ex){
		return ex.getMessage();
	}

}
