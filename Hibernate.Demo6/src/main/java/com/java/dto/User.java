package com.java.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="\"User\"")
@NoArgsConstructor
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Audited
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
