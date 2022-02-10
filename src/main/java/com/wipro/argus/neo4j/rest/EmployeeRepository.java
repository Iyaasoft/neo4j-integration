package com.wipro.argus.neo4j.rest;

import com.wipro.argus.neo4j.domain.Employee;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends Neo4jRepository<Employee, UUID> {

    List<Employee> findAllByDepartmentId(UUID departmentId);
}
