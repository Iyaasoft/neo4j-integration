package com.wipro.argus.neo4j.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.argus.neo4j.domain.Department;
import com.wipro.argus.neo4j.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.UUID;

@Slf4j
@RequestMapping("/api/v1/department")
@RestController
public class DepartmentController {
   private final ObjectMapper mapper;
   private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService, ObjectMapper mapper) {
        this.departmentService = departmentService;
        this.mapper =mapper;
    }

    @PostMapping()
    public ResponseEntity<Department> storeDepartment(@RequestBody String department) {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        log.debug("++++++++++++++`\n"+department);
        Department dept;
        try {
            dept = mapper.readValue(department, Department.class);
            return new ResponseEntity(departmentService.createDepartmentAndEmployees(dept), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> storeDepartment(@PathVariable UUID departmentId) {

        return new ResponseEntity(departmentService.getDepartmentAndEmployees(departmentId), HttpStatus.OK);
    }

}
