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
		tx.commit();
		session.close();
	}

	public void updateEmployee(Employee e) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(e);
		tx.commit();
	}

	public void deleteEmployee(int id) {
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		/*
		 * Employee e= new Employee(); e.setId(id); session.delete(e);
		 */
		Employee e = session.load(Employee.class, id);
		session.delete(e);
		tx.commit();

	}

	public Employee getEmployeeById(int id) {
		Session session = factory.openSession();
	/*	return session.get(Employee.class, id);*/
		return session.load(Employee.class, id);
	}

	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

}
