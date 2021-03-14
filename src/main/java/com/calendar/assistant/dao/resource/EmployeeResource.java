package com.calendar.assistant.dao.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calendar.assistant.dao.EmployeeDao;
import com.calendar.assistant.entity.Employee;

@Component
public class EmployeeResource
{
    @Autowired
    private EmployeeDao employeeDao;

    public Employee findEmployeeByRank(int rank)
    {
        return employeeDao.findByRank(rank);
    }

    public Employee findEmployeeByDesignation(String designation)
    {
        return employeeDao.findByDesignation(designation);
    }

    public Employee findByEmployeeName(String employeeName)
    {
        return employeeDao.findByEmployeeName(employeeName);
    }

    public List<Employee> getAllEmployees()
    {
        return (List<Employee>) employeeDao.findAll();
    }

}
