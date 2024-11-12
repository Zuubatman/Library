package com.library.managment.library_mgmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryMgmtApplication {

	public static void main(String[] args) {

//		MongoOperations mongoOps = new MongoTemplate(MongoClients.create(), "database");
//		mongoOps.insert(new Person("Joe", 34));
//
//		System.out.println(mongoOps.query(Person.class).matching(where("name").is("Joe")).firstValue());
//
//		mongoOps.dropCollection("person");

		SpringApplication.run(LibraryMgmtApplication.class, args);
	}

}