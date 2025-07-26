package com.example.backend.Service.Impl;

import com.example.backend.Controller.request.EmployeePageRequest;
import com.example.backend.Entity.Employee;
import com.example.backend.Mapper.EmployeeMapper;
import com.example.backend.Service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
//必须引用Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public List<Employee> SelectEmployees() {
        return employeeMapper.SelectEmployees();
    }

    @Override
    public Object page(EmployeePageRequest employeePageRequest) {
        String name=employeePageRequest.getEmployeeName();
        PageHelper.startPage(employeePageRequest.getPageNum(), employeePageRequest.getPageSize());
        List<Employee> employees = employeeMapper.listByCondition(employeePageRequest);
        return new PageInfo<>(employees);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMapper.addEmployee(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    @Override
    public void deleteEmployee(String id) {
        employeeMapper.deleteEmployee(id);
    }

    @Override
    public List<Map<String, Object>> getEmployeeTypeCounts() {
        return employeeMapper.getEmployeeTypeCounts();
    }

    @Override
    public void saveBatch(List<Employee> employeeList) {
        for(Employee employee : employeeList) {
            employeeMapper.addEmployee(employee);
        }
    }
}
