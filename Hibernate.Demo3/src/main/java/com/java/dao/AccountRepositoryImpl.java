package com.java.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import org.postgresql.Driver;

import com.java.dto.Account;

public class AccountRepositoryImpl {
	SessionFactory sf;
	{
		Configuration cfg= new Configuration().addPackage("com.java.dto");
		cfg.addAnnotatedClass(Account.class);
		cfg.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
		cfg.setProperty("hibernate.connection.username","postgres");
		cfg.setProperty("hibernate.connection.password","postgres");
		cfg.setProperty("hibernate.connection.driver_class",Driver.class.getName());
	/*	cfg.setProperty(Environment.HBM2DDL_AUTO, "create");*/
		cfg.setProperty(Environment.SHOW_SQL, "true");
		cfg.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
		StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().
				applySettings(cfg.getProperties());
		 sf=cfg.buildSessionFactory(builder.build());
		
	}
	public List<Account> getAllAccounts(){
		Session s=sf.openSession();
		Query<Account> query= s.createQuery("from Account", Account.class);
		 List<Account> list= query.list();
		 s.close();
		 return list;
		}
	
	public List<Account> getAllAccountsByPage(int pageNumber, int size){
		Session s=sf.openSession();
		Query<Account> query= s.createQuery("from Account", Account.class);
		query.setFirstResult((pageNumber-1)*size);
		query.setMaxResults(size);
		 List<Account> list= query.list();
		 s.close();
		 return list;
		}
	
	public Account getAccountByNumber(int accountNumber){
		Session s=sf.openSession();
		Query<Account> query= s.createQuery("from Account where accountNumber= "+accountNumber , Account.class);
		Account ac= query.uniqueResult();
		s.close();
		return ac;
		}
	
	public Float getBalanceOfAccountByNumber(int accountNumber){
		Session s=sf.openSession();
		Query<Float> query= s.createQuery("select balance from Account where accountNumber= "+accountNumber , Float.class);
		Float ac= query.uniqueResult();
		s.close();
		return ac;
		}
	
	public void insertAccount(Account account){
	/*
	 * Cannot be done in hql
	 * U can only only insert data from one table to another
	 * acc1	acc2
	 * insert into acc1 values (select * from acc2);
	 * insert into acc1 values(account.getNumber)
	 * */
		Session s=sf.openSession();
		Transaction tx=s.beginTransaction();
		s.save(account);
		tx.commit();
		s.close();
		}
	public int updateAccount(Account account){
		Session s=sf.openSession();
		Transaction tx=s.beginTransaction();
		Query q=s.createQuery("update Account set version = ?1, balance =?2 where version= ?3 and accountNumber =?4");
		q.setParameter(1, account.getVersion()+1);
		q.setParameter(2, account.getBalance());
		q.setParameter(3, account.getVersion());
		q.setParameter(4, account.getAccountNumber());
		int rows=q.executeUpdate();
		tx.commit();
		s.close();
		return rows;
	}
	
	
	public int deletAccount(int accountNumber){
		Session s=sf.openSession();
		Transaction tx=s.beginTransaction();
		Query q=s.createQuery("delete Account where accountNumber =?1");
		q.setParameter(1, accountNumber);
		int rows=q.executeUpdate();
		tx.commit();
		s.close();
		return rows;
	}
	
	public void close() {
		sf.close();
	}
}
