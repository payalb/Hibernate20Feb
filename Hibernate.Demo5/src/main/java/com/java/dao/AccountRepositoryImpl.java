package com.java.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.postgresql.Driver;

import com.java.dto.Account;
import com.java.dto.SavingAccount;

public class AccountRepositoryImpl {
	static SessionFactory sf;
static	{
		Configuration cfg= new Configuration().addPackage("com.java.dto");
		cfg.addAnnotatedClass(Account.class);
		cfg.addAnnotatedClass(SavingAccount.class);
		cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
		cfg.setProperty("hibernate.connection.username","postgres");
		cfg.setProperty("hibernate.connection.password","postgres");
		cfg.setProperty("hibernate.connection.driver_class",Driver.class.getName());
		cfg.setProperty(Environment.HBM2DDL_AUTO, "create");
		cfg.setProperty(Environment.SHOW_SQL, "true");
		cfg.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
		StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().
				applySettings(cfg.getProperties());
		 sf=cfg.buildSessionFactory(builder.build());
		
	}
	public static void main(String[] args) {
		Account ac1= new Account(123,5000, 0);
		Account ac2= new SavingAccount(476,5000, "Payal");
		Session s=sf.openSession();
		s.beginTransaction();
		s.save(ac1);
		s.save(ac2);
		s.getTransaction().commit();
		s.close();
	}
	
	public void close() {
		sf.close();
	}
}
