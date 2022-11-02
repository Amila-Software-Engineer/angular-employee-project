package com.example.angularemployee.controller;

import com.example.angularemployee.dao.EmployeeDao;
import com.example.angularemployee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping
    public List<Employee> getAll(){
        return employeeDao.findAll();
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Integer id){
        Optional<Employee> optionalEmployee = employeeDao.findById(id);

        if(optionalEmployee.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found.");
        return optionalEmployee.get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        if(!employeeDao.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found.");
        employeeDao.deleteById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee add(@RequestBody Employee employee){
        return employeeDao.save(employee);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee employee){
        if(!employeeDao.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found.");

        employee.setId(id);
        return employeeDao.save(employee);
    }

}
