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
	/*Criteria: select*/
	public List<Account> getAllAccounts(){
		Session s=sf.openSession();
		Criteria criteria=s.createCriteria(Account.class); //from account
		return criteria.list();
		}
	
	public List<Account> getAllAccountsByPage(int pageNumber, int size){
		Session s=sf.openSession();
		Criteria criteria=s.createCriteria(Account.class); //from account
		criteria.setFirstResult(size*(pageNumber-1));
		criteria.setMaxResults(size);
		return criteria.list();
		}
	
	public Account getAccountByNumber(int accountNumber){
		Session s=sf.openSession();
		Criteria criteria=s.createCriteria(Account.class); //from account
		//where: Restriction
		 criteria.add(Restrictions.idEq(accountNumber));//where id= accountNumber
		return (Account) criteria.uniqueResult();
		}
	
	public List<Account> findAccountsWithBalanceLT500AndVersionGT0(){
		Session s=sf.openSession();
		Criteria criteria=s.createCriteria(Account.class); //from account
		//where: Restriction
		 criteria.add(Restrictions.lt("balance", 500));//where id= accountNumber
		 criteria.add(Restrictions.gt("version", 0));//and
		return criteria.list();
		}
	

	public List<Account> findAccountsWithBalanceLT500OrVersionGT0(){
		Session s=sf.openSession();
		Criteria criteria=s.createCriteria(Account.class); //from account
		//where: Restriction
		 criteria.add(Restrictions.or(Restrictions.lt("balance", 500),Restrictions.gt("version", 0)));//where id= accountNumber
		return criteria.list();
		}
	public Float getBalanceOfAccountByNumber(int accountNumber){
		Session s=sf.openSession();
		//Projection: select a particular column/ aggregate function
		Criteria criteria=s.createCriteria(Account.class); //from account
		 criteria.add(Restrictions.idEq(accountNumber));//where id= accountNumber
		 criteria.setProjection(Projections.property("balance"));//select balance from account
		return  (Float) criteria.uniqueResult();
		}
	
	public void insertAccount(Account account){
		/**/
		}
	public int updateAccount(Account account){
		Session s=sf.openSession();
		s.beginTransaction();
		CriteriaBuilder builder=s.getCriteriaBuilder();
		 CriteriaUpdate<Account> cr=builder.createCriteriaUpdate(Account.class);
		Root<Account> root= cr.from(Account.class);
			cr.set("balance", account.getBalance()-500);
			cr.set("version", account.getVersion()+1);
			/*cr.where(builder.equal(root.get("accountNumber"),account.getAccountNumber()));*/
		int x=	s.createQuery(cr).executeUpdate();
		s.getTransaction().commit();
		return x;
	}
	
/*	
	public int deletAccount(int accountNumber){
	
	}*/
	
	public void close() {
		sf.close();
	}
}
