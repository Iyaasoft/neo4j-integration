package com.wipro.argus.neo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Node
public class Manager {

    @Id
    @GeneratedValue
    private UUID id;

    @Relationship(type = "Manages" , direction = Relationship.Direction.OUTGOING)
    Department department;
    private String firstName;
    private String lastName;

}
