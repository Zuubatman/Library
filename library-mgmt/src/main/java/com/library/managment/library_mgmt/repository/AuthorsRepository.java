package com.library.managment.library_mgmt.repository;

import com.library.managment.library_mgmt.entities.Author;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorsRepository {
    final private MongoOperations mongoOps;

    public AuthorsRepository(MongoOperations mongoOperations){
        this.mongoOps = mongoOperations;
    }

    public List<Author> getAllAuthors(){
        return mongoOps.findAll(Author.class);
    }
}
