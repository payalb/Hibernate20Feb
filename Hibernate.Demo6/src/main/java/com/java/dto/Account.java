package com.java.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class Account {
	@Column(name="ano")
	@Id
	int accountNumber;
	float balance;
	int version;
	@ManyToMany(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
	List<User> users;
}
