package com.wipro.argus.neo4j.exception;

public class DepartmentNotFoundException extends RuntimeException {

    private static final String DEPARTMENT_NOT_FOUND = "Department requested not found";

    public DepartmentNotFoundException() {
        super(DEPARTMENT_NOT_FOUND);
    }
}
