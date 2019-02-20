package com.java.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.java.dto.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepository {
	SessionFactory factory;
	{
		Configuration cfg = new Configuration();
		cfg.configure("hibernate-config.xml");// session factory object: schema
		factory = cfg.buildSessionFactory();
	}

	public void addEmployee(Employee e) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(e);
		//session.update(object);
		/*session.delete(object);*/
		/*Employee e=session.get(Employee.class, 1);
		 
		 */
	
		tx.commit();
	}

	public void updateEmployee(Employee e) {
		// TODO Auto-generated method stub

	}

	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub

	}

	public Employee getEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

}
