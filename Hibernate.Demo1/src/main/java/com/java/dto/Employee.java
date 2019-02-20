package com.java.dto;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity	
public class Employee {
	@Id
	@GeneratedValue/*(strategy=GenerationType.AUTO)*/
	private int id;
	private String name;
	@ElementCollection
	private List<String> projects;
	private float salary;
	private boolean isActive;
}
/*employee
id	name	projects						salary	isActive	Projects
																id name
			Java Training, J2ee training,						1 Java Training
																1 J2ee Training
			Pl/sql, sql training*/