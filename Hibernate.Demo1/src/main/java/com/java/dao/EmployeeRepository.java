package com.java.dao;

import java.util.List;

import com.java.dto.Employee;

public interface EmployeeRepository {

	public void addEmployee(Employee e);
	public void updateEmployee(Employee e);
	public void deleteEmployee(int id);
	public Employee getEmployeeById(int id);
	public List<Employee> getEmployees();
}
