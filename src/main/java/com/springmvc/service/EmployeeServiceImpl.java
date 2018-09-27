package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.IEmployeeDao;
import com.springmvc.model.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao dao;
	
	public Employee findById(int id) {
		return dao.findById(id);
	}

	public void saveEmployee(Employee employee) {
		dao.saveStudent(employee);
	}
	
	public void updateEmployee(Employee employee) {
		Employee entity = dao.findById(employee.getId());
		if(entity!=null){
			entity.setFirstName(employee.getFirstName());
			entity.setLastName(employee.getLastName());;
			entity.setEnteringDate(employee.getEnteringDate());
			entity.setNationality(employee.getNationality());
			entity.setCode(employee.getCode());
			//dao.saveOrUpdate(student);
		}
	}

	public void deleteEmployeeByNumber(String ssn) {
		dao.deleteEmployeeByNumber(ssn);
	}
	
	public List<Employee> findAllEmployees(String searchKeyword) {
		return dao.findAllEmployees(searchKeyword);
	}

	public Employee findEmployeeByNumber(String empNumber) {
		return dao.findEmployeeByNumber(empNumber);
	}

	public boolean isEmployeeNumberUnique(Integer id, String empNumber) {
		Employee employee = findEmployeeByNumber(empNumber);
		return ( employee == null || ((id != null) && (employee.getId() == id)));
	}
	
}
