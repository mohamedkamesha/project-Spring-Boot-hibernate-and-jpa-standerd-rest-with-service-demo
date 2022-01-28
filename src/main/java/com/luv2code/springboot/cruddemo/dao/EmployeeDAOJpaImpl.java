package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> findAll() {
		//no session in JPA
		// jap call persistence
		Query query = entityManager.createQuery("from Employee");
		List<Employee> employees = query.getResultList();
		
		// in one row
		//List<Employee> employees = entityManager.createQuery("from Employee").getResultList();
		return employees;
	}

	@Override
	public Employee findById(int id) {
		 Employee employee = entityManager.find(Employee.class, id);
		return employee;
	}

	@Override
	public void save(Employee employee) {
		//if 0 save else update
		  Employee employee2 = entityManager.merge(employee);

		 //update with id from db 
		 employee.setId(employee2.getId());
	}

	@Override
	public void deleteById(int id) {
		Query query = entityManager.createQuery("delete from Employee where id=:theId"); 
		query.setParameter("theId", id);
		query.executeUpdate();
	}

}
