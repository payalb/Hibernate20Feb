package com.java;

import com.java.dao.AccountRepositoryImpl;
import com.java.dto.Account;

public class Main {
	public static void main(String[] args) {
		AccountRepositoryImpl obj= new AccountRepositoryImpl();
	obj.insertAccount(new Account(12345,5000, 0));
	System.out.println(obj.getBalanceOfAccountByNumber(12345));
		obj.close();
	}
}
