package ru.listset.collections.services.impl;

import org.springframework.stereotype.Service;
import ru.listset.collections.exeptions.EmployeeAlreadyAddedException;
import ru.listset.collections.exeptions.EmployeeNotFoundException;
import ru.listset.collections.exeptions.EmployeeStorageIsFullException;
import ru.listset.collections.model.Employee;
import ru.listset.collections.services.EmployeeService;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final int maxEmployeesNumber = 7;

    private final Map<String, Employee> employees = new HashMap<>();
    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= maxEmployeesNumber) {
            throw new EmployeeStorageIsFullException("Достигнуто максимальное число сотрудников");
        }
        if (employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " уже есть в списке");
        }

        Employee employee = new Employee(firstName, lastName);
        employees.put(getKey(employee), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        if (!employees.containsKey(getKey(firstName, lastName))) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден");
        }
        return employees.remove(getKey(firstName, lastName));
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = employees.get(getKey(firstName, lastName));
        if (employee == null) {
            throw new EmployeeNotFoundException("Сотрудник с именем " + firstName + " и фамилией " + lastName + " не найден");
        }
        return employee;
    }

    @Override
    public Map<String, Employee> getAll() {
        return Collections.unmodifiableMap(employees);
    }

    private static String getKey(String firstName, String lastName) {
        return firstName + lastName;
    }
    private static String getKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }
}
