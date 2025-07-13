package com.example.employeeservice.dao;

import com.example.employeeservice.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Employee> getAll() {
        return jdbcTemplate.query("SELECT * FROM employee", new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public int save(Employee emp) {
        return jdbcTemplate.update("INSERT INTO employee(name, department, position, salary) VALUES (?, ?, ?, ?)",
                emp.getName(), emp.getDepartment(), emp.getPosition(), emp.getSalary());
    }

    public int update(Employee emp) {
        return jdbcTemplate.update("UPDATE employee SET name=?, department=?, position=?, salary=? WHERE id=?",
                emp.getName(), emp.getDepartment(), emp.getPosition(), emp.getSalary(), emp.getId());
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM employee WHERE id=?", id);
    }
}