package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	@Autowired
	private EntityManager entityManager ;
	/*
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager=entityManager;
	}*/
	
	@Override
	@Transactional
	public List<Employee> findAll() {
		
		Session session = entityManager.unwrap(Session.class); 
		Query<Employee> query = session.createQuery("from Employee",Employee.class);
		List<Employee> employees = query.getResultList();
		return employees;
	}
	
	@Override
	public Employee findById(int id) {
		 
		Session session = entityManager.unwrap(Session.class); 
		Employee employee = session.get(Employee.class, id);
		
		return employee;
	}
	
	@Override
	public void save(Employee employee) {

		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(employee);

	}
	
	@Override
	public void deleteById(int id) {
		
		Session session = entityManager.unwrap(Session.class); 
		Query query =session.createQuery("delete from Employee where id=:theId");
		query.setParameter("theId", id);
		query.executeUpdate();
		
	}
	
	
	

}
