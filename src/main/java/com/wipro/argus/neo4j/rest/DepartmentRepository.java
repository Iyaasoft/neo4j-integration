package com.wipro.argus.neo4j.rest;

import java.util.List;
import java.util.UUID;

import com.wipro.argus.neo4j.domain.Department;
import com.wipro.argus.neo4j.domain.Employee;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends Neo4jRepository<Department, UUID> {

    List<Department> findByName(@Param("name") String name);
}
