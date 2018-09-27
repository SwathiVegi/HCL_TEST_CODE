package com.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;

import com.springmvc.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer, Employee>implements IEmployeeDao {

	public Employee findById(int id) {
		return getByKey(id);
	}

	public void saveStudent(Employee employee) {
		persist(employee);
	}

	public void saveOrUpdate(Employee employee) {
		super.saveOrUpdate(employee);
	}

	public void deleteEmployeeByNumber(String code) {
		/*
		 * Query query = getSession().createSQLQuery(
		 * "delete from Employee where code = :code"); query.setString("code",
		 * code); query.executeUpdate();
		 */
		super.delete(findEmployeeByNumber(code));

	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees(String searchKeyword) {
		Criteria criteria = createEntityCriteria();

		if (searchKeyword != null && searchKeyword.trim().length() != 0) {
			
			SimpleExpression fNameCriteria = Restrictions.like("firstName", "%" + searchKeyword + "%").ignoreCase();
			SimpleExpression lNameCriteria = Restrictions.like("lastName", "%" + searchKeyword + "%").ignoreCase();
			SimpleExpression codeCriteria = Restrictions.like("code", "%" + searchKeyword + "%").ignoreCase();
			
			Disjunction whereClauses = Restrictions.or(fNameCriteria, lNameCriteria , codeCriteria);
			
			criteria.add(whereClauses);
			
		}

		return (List<Employee>) criteria.list();
	}

	public Employee findEmployeeByNumber(String code) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("code", code));
		return (Employee) criteria.uniqueResult();
	}
}
