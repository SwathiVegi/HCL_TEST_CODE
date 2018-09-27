package com.springmvc.dao;

import java.util.List;

import com.springmvc.model.Employee;

public interface IEmployeeDao {

	Employee findById(int id);

	void saveEmployee(Employee employee);
	
	public void saveOrUpdate(Employee employee);
	
	void deleteEmployeeByNumber(String ssn);
	
	List<Employee> findAllEmployees(String searchKeyword);

	Employee findEmployeeByNumber(String ssn);

}
