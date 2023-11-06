package ru.listset.collections.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.listset.collections.model.Employee;
import ru.listset.collections.services.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private final EmployeeService employeeService;

    @GetMapping("/add")
    public Employee add (
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        return employeeService.add(firstName, lastName);
    }
    @GetMapping("/remove")
    public Employee remove (
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        return employeeService.remove(firstName, lastName);
    }
    @GetMapping("/find")
    public Employee find (
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        return employeeService.find(firstName, lastName);
    }
    @GetMapping
    public List<Employee> getAll () {
        return employeeService.getAll();
    }
}
