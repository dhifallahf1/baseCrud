package com.fd.baseCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRespository employeeRespository;

    public List<Employee> getEmployees (){
        return employeeRespository.findAll();
    }

    public Employee save(Employee employee) {
       // employeeRespository.save(employee);
        return employeeRespository.save(employee);
    }

    public Employee update(Employee employee) {
        Optional<Employee> empl = employeeRespository.findById(employee.id);
        if (empl.isPresent()){
           return employeeRespository.save(employee);
        }
        else
            return null;
    }

    public Optional<Employee> getEmployee(Long id) {
        return employeeRespository.findById(id);
    }
}
