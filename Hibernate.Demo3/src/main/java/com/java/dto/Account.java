package com.java.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
//@Table(name="acc")
@Data
@AllArgsConstructor
public class Account {
	@Column(name="ano")
	@Id
	int accountNumber;
	float balance;
	int version;
}
