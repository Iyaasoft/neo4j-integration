package com.wipro.argus.neo4j.service;

import com.wipro.argus.neo4j.domain.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface DepartmentService {

    Department createDepartmentAndEmployees(Department department);

    Department getDepartmentAndEmployees(UUID departmentId);

    Object getEmployees(UUID departmentId);
}
