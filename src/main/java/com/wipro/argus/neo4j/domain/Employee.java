package com.wipro.argus.neo4j.domain;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;

@Node
public class Employee {

    @Id @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    @Relationship(type="Line Manager", direction = Relationship.Direction.INCOMING)
    private Manager manager;

    public Employee(UUID id, String firstName, String lastName, Manager manager) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.manager = manager;
    }

    public Employee() {
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String  getName() {
        return getFirstName() +" "+getLastName();
    }
}
