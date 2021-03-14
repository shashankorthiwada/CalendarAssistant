package com.calendar.assistant.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calendar.assistant.entity.Employee;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long>
{
    Employee findByRank(int rank);

    Employee findByDesignation(String designation);

    Employee findByEmployeeName(String employeeName);
}
