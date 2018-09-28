package com.springmvc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.IEmployeeDao;
import com.springmvc.model.Employee;
import com.springmvc.queue.MessageSender;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements IEmployeeService {
	static Logger log = Logger.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private IEmployeeDao dao;
	
	
	@Autowired
    MessageSender messageSender;
	
	public Employee findById(int id) {
		return dao.findById(id);
	}

	public void saveEmployee(Employee employee) {
		dao.saveEmployee(employee);
		//we can externalize this parameter in a config file, as of now hard coding in the code.
		final boolean enableMessaging = false;
		if(enableMessaging){
			this.publishEmployeeCreationMessage(employee);
		}
	}
	
	protected void publishEmployeeCreationMessage(Employee employee){
		log.info("Application : sending JMS EMployee Creation request =" + employee);
        	messageSender.sendMessage(employee);
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	public void updateEmployee(Employee employee) {
		Employee entity = dao.findById(employee.getId());
		if(entity!=null){
			entity.setFirstName(employee.getFirstName());
			entity.setLastName(employee.getLastName());;
			entity.setEnteringDate(employee.getEnteringDate());
			entity.setNationality(employee.getNationality());
			entity.setCode(employee.getCode());
			dao.saveEmployee(entity);
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
