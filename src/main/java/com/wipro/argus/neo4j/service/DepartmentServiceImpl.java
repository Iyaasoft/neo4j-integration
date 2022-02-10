package com.wipro.argus.neo4j.service;

import com.wipro.argus.neo4j.domain.Department;
import com.wipro.argus.neo4j.domain.Employee;
import com.wipro.argus.neo4j.exception.DepartmentNotFoundException;
import com.wipro.argus.neo4j.rest.DepartmentRepository;
import com.wipro.argus.neo4j.rest.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;
    EmployeeRepository employeeRepository;
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Department createDepartmentAndEmployees(Department department) {
        Department depmnt = departmentRepository.save(department);
        depmnt.getEmployees().forEach(emp -> { emp.setDepartmentId(depmnt.getId().toString()); employeeRepository.save(emp);});

        return depmnt;
    }

    @Override
    public Department getDepartmentAndEmployees(UUID departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException());
    }

    @Override
    public List<Employee> getEmployees(UUID departmentId) {
        return  employeeRepository.findAllByDepartmentId(departmentId);
    }
}
