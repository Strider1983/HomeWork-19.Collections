package ru.listset.collections.services.impl;

import org.springframework.stereotype.Service;
import ru.listset.collections.exeptions.EmployeeAlreadyAddedException;
import ru.listset.collections.exeptions.EmployeeNotFoundException;
import ru.listset.collections.exeptions.EmployeeStorageIsFullException;
import ru.listset.collections.model.Employee;
import ru.listset.collections.services.EmployeeService;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int maxEmployeesNumber = 7;

    private final List<Employee> employees = new ArrayList<>();
    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= maxEmployeesNumber) {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное число сотрудников");
        }
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " уже есть в списке");
        }


        employees.add(new Employee(firstName, lastName));
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден");
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee requestedEmployee = new Employee(firstName, lastName);
        for (Employee employee : employees) {
            if (employee.equals(requestedEmployee)) {
                return requestedEmployee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден");
    }

    @Override
    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
