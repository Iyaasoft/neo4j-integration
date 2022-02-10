package com.wipro.argus.neo4j.domain;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;


@Node
public class Manager {

    @Id
    @GeneratedValue
    private UUID id;

    @Relationship(type = "manages" , direction = Relationship.Direction.OUTGOING)
    Department department;

    private String firstName;
    private String lastName;

    public Manager(UUID id, Department department, String firstName, String lastName) {
        this.id = id;
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Manager() {
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
