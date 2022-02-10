package com.wipro.argus.neo4j;

import com.wipro.argus.neo4j.model.Person;
import com.wipro.argus.neo4j.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.List;

@Slf4j
@EnableTransactionManagement
@EnableNeo4jRepositories
@SpringBootApplication
public class Neo4jIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jIntegrationApplication.class, args);
	}

	@Bean
	CommandLineRunner demo(PersonRepository personRepository) {
		return args -> {

			personRepository.deleteAll();

			Person greg = new Person("Greg");
			Person roy = new Person("Roy");
			Person craig = new Person("Craig");
			Person stephen = new Person("Stephen");
			Person allison = new Person("Allison");
			Person mary = new Person("Mary");


			List<Person> team = Arrays.asList(greg, roy, craig, stephen, allison, mary);

			log.info("Before linking up with Neo4j...");

			team.stream().forEach(person -> log.info("\t" + person.toString()));

			personRepository.save(greg);
			personRepository.save(roy);
			personRepository.save(craig);
			personRepository.save(stephen);
			personRepository.save(allison);
			personRepository.save(mary);

			greg = personRepository.findByName(greg.getName());
			greg.worksWith(roy);
			greg.worksWith(craig);
			personRepository.save(greg);

			stephen = personRepository.findByName(stephen.getName());
			stephen.worksWith(mary);
			stephen.worksWith(roy);
			personRepository.save(stephen);

			roy = personRepository.findByName(roy.getName());
			roy.worksWith(craig);
			roy.worksWith(stephen);
			// We already know that roy works with greg
			personRepository.save(roy);

			// We already know craig works with roy and greg

			log.info("Lookup each person by name...");
			team.stream().forEach(person -> log.info(
					"\t" + personRepository.findByName(person.getName()).toString()));

			List<Person> teammates = personRepository.findByTeammatesName(greg.getName());
			log.info("The following have Greg as a teammate...");
			teammates.stream().forEach(person -> log.info("\t" + person.getName()));
		};
	}

}

