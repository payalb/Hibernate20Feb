package com.java.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.java.dto.Employee;
import com.java.dto.SocialSecurityNumber;

public class MyMain {
	public static void main(String[] args) {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate-config.xml");// session factory object: schema
			SessionFactory factory = cfg.buildSessionFactory();
			
			SocialSecurityNumber number= new SocialSecurityNumber(3663_6353_6454l,"Chicago");
			Employee e1= Employee.builder().name("Payal").ssn(number).build();
			Session session1=factory.openSession();
			Transaction tx=session1.beginTransaction();
			session1.save(number);
			session1.save(e1);
			tx.commit();
			session1.close();
			
			Session session2=factory.openSession();
			Employee e=session2.get(Employee.class, 1);//ssn details fetched (join query)
			System.out.println("Employee fetched");
			System.out.println(e.getSsn().getCity());
			session2.close();
			
			factory.close();
	}
}
/*employee			ssn
1 payal	ssn_id			366363536454	chicago*/