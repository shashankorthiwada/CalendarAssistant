package com.calendar.assistant;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.calendar.assistant.dao.EmployeeDao;
import com.calendar.assistant.entity.Employee;

@SpringBootApplication
public class CalendarAssistantApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(CalendarAssistantApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeDao repository)
    {
        return (args) -> {

            repository.save(new Employee("Employee1", "Director", 1));
            repository.save(new Employee("Employee2", "Manager", 2));
            repository.save(new Employee("Employee3", "TechLead", 3));
            repository.save(new Employee("Employee4", "Sr. Software Engineer", 4));
            repository.save(new Employee("Employee5", "Software Engineer", 5));
        };

    }

}
