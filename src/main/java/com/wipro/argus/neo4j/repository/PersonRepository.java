package com.wipro.argus.neo4j.repository;

import java.util.List;

import com.wipro.argus.neo4j.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);
    List<Person> findByTeammatesName(String name);
}