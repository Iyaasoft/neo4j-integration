package com.wipro.argus.neo4j.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Node
public class ScrumTeam {

    @Id
    private String name;
    @Relationship(type = "Team Member" , direction = Relationship.Direction.INCOMING)
    Set<Employee> members;
}
