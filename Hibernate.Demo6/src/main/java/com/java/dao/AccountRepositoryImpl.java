package com.java.dao;

import java.util.List;

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
		/*cfg.setProperty(Environment.HBM2DDL_AUTO, "create");*/
		cfg.setProperty(Environment.SHOW_SQL, "true");
		cfg.setProperty(Environment.GENERATE_STATISTICS, "true");
		cfg.setProperty(Environment.USE_QUERY_CACHE, Boolean.TRUE.toString());
		cfg.setProperty(Environment.USE_SECOND_LEVEL_CACHE, "true");
		cfg.setProperty(Environment.CACHE_REGION_FACTORY, "org.hibernate.cache.ehcache.internal.EhcacheRegionFactory");
		cfg.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
		StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().
				applySettings(cfg.getProperties());
		 sf=cfg.buildSessionFactory(builder.build());
		
	}
	public static void main(String[] args) {
	/*	User user1= new User("Payal","payal@rjt", 76474344);
		User user2= new User("Parul","parul@rjt", 74364376);
		Account ac1= new Account(123,5000, 0, Arrays.asList(user1, user2));
		Account ac2= new Account(124,2500, 0, Arrays.asList(user1));
		Session s=sf.openSession();

		s.beginTransaction();
		s.persist(ac1);//save it in 1st level cache
		s.persist(ac2);*/
		/*Query<User> q=s.createQuery("from User where id= 1", User.class);
		q.setCacheable(true);
		q.uniqueResult();
		Query<User>  q1=s.createQuery("from User where id= 1", User.class);
		 q1.setCacheable(true);
		 q1.uniqueResult();
		//s.get(Account.class, 123);//it will not fire any query to db, will give data from the cache.
	//	System.out.println("***");
		Criteria criteria=s.createCriteria(Account.class);
		criteria.add(Restrictions.idEq(123));
		Account ac=(Account) criteria.uniqueResult();//fire the query to db coz it is a criteria.
		System.out.println(ac);
		//hql and criteria: 1st level cache does not work
		Account ac3=(Account) s.createQuery("from Account where accountNumber = 123").uniqueResult();
		s.close();
		System.out.println("***********************************************");
		s= sf.openSession();
		 q1=s.createQuery("from User where id= 1", User.class);
		 q1.setCacheable(true);
		 q1.uniqueResult();*/
		Session s=sf.openSession();
		List<Account> list=s.createQuery("from account").list();//2
		for(Account ac: list) {
		System.out.println(ac.getUsers());
		}
		s.close();
	}
	
	public void close() {
		sf.close();
	}
}
