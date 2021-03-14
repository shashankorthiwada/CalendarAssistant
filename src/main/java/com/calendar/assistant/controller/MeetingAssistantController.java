package com.calendar.assistant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendar.assistant.dao.resource.EmployeeResource;
import com.calendar.assistant.dto.Calendar;
import com.calendar.assistant.dto.CalendarResponseDTO;
import com.calendar.assistant.entity.Employee;
import com.calendar.assistant.service.MeetingAssistantService;

@RestController
@RequestMapping("api/calendar/meeting")
public class MeetingAssistantController
{
    @Autowired
    private EmployeeResource employeeResource;

    @Autowired
    private MeetingAssistantService meetingAssistingService;

    @GetMapping("/getEmployee/rank/{rank}")
    public Employee getEmployeeByRank(@PathVariable("rank") int rank)
    {
        return employeeResource.findEmployeeByRank(rank);
    }

    @GetMapping("/getEmployee/designation/{designation}")
    public Employee getEmployeeByDesignation(@PathVariable("designation") String designation)
    {
        return employeeResource.findEmployeeByDesignation(designation);
    }

    @GetMapping("/getEmployee/employeeName/{employeeName}")
    public Employee getEmployeeByName(@PathVariable("employeeName") String employeeName)
    {
        return employeeResource.findByEmployeeName(employeeName);
    }

    @GetMapping("/employee/getAll")
    public List<Employee> getAllEmployees()
    {
        return employeeResource.getAllEmployees();
    }

    @PostMapping("/resolveconflicts")
    public CalendarResponseDTO resolveMeetingConflicts(@RequestBody Calendar calendar)
    {
        return meetingAssistingService.resolveMeetingConflicts(calendar);
    }

}
