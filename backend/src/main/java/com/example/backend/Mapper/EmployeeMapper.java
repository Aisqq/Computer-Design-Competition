package com.example.backend.Mapper;

import com.example.backend.Controller.request.EmployeePageRequest;
import com.example.backend.Entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {

//用注解方法操作表
//    @Select("select * from Employee")
//    List<Employee> SelectEmployees();

//用xml文件操作表
    List<Employee> SelectEmployees();

    List<Employee> listByCondition(EmployeePageRequest employeePageRequest);


    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(String id);

    List<Map<String, Object>> getEmployeeTypeCounts();
}
