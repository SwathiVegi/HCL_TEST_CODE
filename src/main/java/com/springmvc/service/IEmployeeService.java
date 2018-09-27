package com.springmvc.service;

import java.util.List;

import com.springmvc.model.Employee;

public interface IEmployeeService {

	Employee findById(int id);
	
	void saveEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployeeByNumber(String code);

	List<Employee> findAllEmployees(String searchKeyword); 
	
	Employee findEmployeeByNumber(String code);

	boolean isEmployeeNumberUnique(Integer id, String code);
	
}
