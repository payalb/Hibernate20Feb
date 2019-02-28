package com.java.dao;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.postgresql.Driver;

import com.java.dto.Account;
import com.java.dto.User;

public class AccountRepositoryImpl {
	static SessionFactory sf;
static	{
		Configuration cfg= new Configuration().addPackage("com.java.dto");
		cfg.addAnnotatedClass(Account.class);
		cfg.addAnnotatedClass(User.class);
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
		User user1= new User("Payal","payal@rjt", 76474344);
		User user2= new User("Parul","parul@rjt", 74364376);
		Account ac1= new Account(123,5000, 0, Arrays.asList(user1, user2));
		Account ac2= new Account(124,2500, 0, Arrays.asList(user1));
		Session s=sf.openSession();

		s.beginTransaction();
		s.persist(ac1);
		s.persist(ac2);
		s.getTransaction().commit();
		s.close();
	}
	
	public void close() {
		sf.close();
	}
}
