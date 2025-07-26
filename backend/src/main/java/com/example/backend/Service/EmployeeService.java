package com.example.backend.Service;

import com.example.backend.Controller.request.EmployeePageRequest;
import com.example.backend.Entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<Employee> SelectEmployees();

    Object page(EmployeePageRequest employeePageRequest);

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(String id);

    List<Map<String, Object>> getEmployeeTypeCounts();

    void saveBatch(List<Employee> employeeList);
}
