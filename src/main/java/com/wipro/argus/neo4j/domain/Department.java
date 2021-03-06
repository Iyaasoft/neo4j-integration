package com.wipro.argus.neo4j.domain;

import lombok.AllArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Node
public class Department {

    private String name;
    @Id  @GeneratedValue
    private UUID id;


    @Relationship(type = "Employee", direction = Relationship.Direction.INCOMING)
    private Set<Employee> employees;

    @Relationship(type="Department Manager", direction = Relationship.Direction.INCOMING)
    private Manager manager;

    @Relationship(type="Scrum Team", direction = Relationship.Direction.OUTGOING)
    private Set<ScrumTeam> scrumTeams;

    public Department(UUID id, String name, Set<Employee> employees, Manager manager) {
        this.id = id;
        this.name = name;
        this.employees = employees;
        this.manager = manager;
    }

    public Department() {
    }

    public Set<ScrumTeam> getScrumTeams() {
        return scrumTeams;
    }

    public void setScrumTeams(Set<ScrumTeam> scrumTeams) {
        if(Objects.isNull(scrumTeams)) {
            return;
        }
        this.scrumTeams = scrumTeams;
    }

    public void setDepartmentEmployees(Employee employee) {
        employees.add(employee);
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String toString() {

        return this.name + "'s Employees => "
                + Optional.ofNullable(this.employees).orElse(
                        Collections.emptySet()).stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        if(Objects.isNull(employees)) {
            return;
        }
        this.employees = employees;
    }
}
