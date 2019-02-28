package com.java.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="\"User\"")
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	int id;
	String name, emailId;
	int phoneNumber;
	
	public User(String name, String emailId, int phoneNumber) {
		this.name= name;
		this.emailId= emailId;
		this.phoneNumber= phoneNumber;
	}
}
