package com.example.employeeservice.controller;

import com.example.employeeservice.dao.EmployeeDAO;
import com.example.employeeservice.model.Employee;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * REST Controller for employee operations.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDAO dao;

    @GetMapping
    public List<Employee> getAll() {
        return dao.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return dao.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Employee emp) {
        dao.save(emp);
        return ResponseEntity.ok("Employee Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody Employee emp) {
        emp.setId(id);
        dao.update(emp);
        return ResponseEntity.ok("Employee Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        dao.delete(id);
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/group-by-department")
    public Map<String, List<Employee>> groupByDepartment() {
        return dao.getAll().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @GetMapping("/high-salary")
    public List<Employee> highSalary() {
        return dao.getAll().stream().filter(e -> e.getSalary() > 50000).collect(Collectors.toList());
    }
}
