package com.java.dto;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.JOINED)
public class SavingAccount extends Account{

	static final float MIN_BALANCE=500;
	
	String holderName;
	
	public SavingAccount(int accountNumber, float balance, String holderName) {
		super(accountNumber, balance, 0);
		this.holderName= holderName;
	}
}
