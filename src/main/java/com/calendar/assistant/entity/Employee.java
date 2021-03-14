package com.calendar.assistant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class Employee
{

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "EMP_NAME")
    private String employeeName;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "RANK")
    private int rank;

    protected Employee()
    {
    }

    public Employee(String employeeName, String designation, int rank)
    {
        this.employeeName = employeeName;
        this.designation = designation;
        this.rank = rank;
    }

    @Override
    public String toString()
    {
        return String.format("Employee[id=%d, EmployeeName='%s', Designation='%s', Rank='%s']", id, employeeName,
                designation, rank);
    }

    public Long getId()
    {
        return id;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }

    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public String getDesignation()
    {
        return designation;
    }

    public void setDesignation(String designation)
    {
        this.designation = designation;
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

}