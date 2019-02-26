package com.java;

import com.java.dao.AccountRepositoryImpl;
import com.java.dto.Account;

public class Main {
	public static void main(String[] args) {
		AccountRepositoryImpl obj= new AccountRepositoryImpl();
		Account a=obj.getAccountByNumber(12345);
		obj.updateAccount(a);
		obj.close();
	}
}
