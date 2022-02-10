package com.wipro.argus.neo4j.service;

import com.wipro.argus.neo4j.domain.Department;
import com.wipro.argus.neo4j.exception.DepartmentNotFoundException;
import com.wipro.argus.neo4j.rest.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartmentAndEmployees(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentAndEmployees(UUID departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException());
    }
}
