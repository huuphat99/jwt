package com.javainuse.bootmysqlcrud.service;

import java.util.List;

import com.javainuse.bootmysqlcrud.entity.response.EmployeeDto;
import com.javainuse.bootmysqlcrud.exception.EmployeeNotFoundException;

public interface EmployeeService {
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	EmployeeDto getEmployeeById(Long employeeId) throws EmployeeNotFoundException;
	List<EmployeeDto> getEmployees();
	void deleteEmployee(Long employeeId) throws EmployeeNotFoundException;
	EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) throws EmployeeNotFoundException;

}