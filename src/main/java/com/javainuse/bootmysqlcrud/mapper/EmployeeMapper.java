package com.javainuse.bootmysqlcrud.mapper;

import com.javainuse.bootmysqlcrud.entity.response.EmployeeDto;
import com.javainuse.bootmysqlcrud.entity.Employee;

public class EmployeeMapper {

	public static EmployeeDto mapToEmployeeDto(Employee employee) {
		EmployeeDto dto = new EmployeeDto();
		dto.setName( employee.getName());
		dto.setDepartment(employee.getDepartment());
		return dto;
	}

	public static Employee mapToEmployee(EmployeeDto employeeDto) {
		Employee employee = new Employee();
		employee.setName(employeeDto.getName());
		employee.setDepartment(employeeDto.getDepartment());
		return employee;
	}

}