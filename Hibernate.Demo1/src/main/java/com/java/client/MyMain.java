package com.java.client;

import java.util.Arrays;

import com.java.dao.EmployeeRepository;
import com.java.dao.EmployeeRepositoryImpl;
import com.java.dto.Employee;

public class MyMain {
	static EmployeeRepository rep= new EmployeeRepositoryImpl();
public static void main(String[] args) {
	
	
	Employee e= new Employee();
	e.setName("Parul");
	e.setSalary(67546743.64f);
	e.setActive(true);
	e.setProjects(Arrays.asList("C Training", "C++ Training"));
	rep.addEmployee(e);
}
}
