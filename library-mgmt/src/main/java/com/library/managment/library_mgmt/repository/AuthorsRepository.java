package com.library.managment.library_mgmt.repository;

import com.library.managment.library_mgmt.entities.Author;
import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public Author getAuthorById(String id){
        return mongoOps.findById(id, Author.class);
    }

    public List<Author> getAuthorByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        return mongoOps.find(query, Author.class);
    }

    public Author addAuthor(Author author){
        return mongoOps.insert(author);
    }

    public DeleteResult deleteAuthor(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoOps.remove(query, Author.class);
    }

    public Author updateAuthor(Author author){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(author.getId()));
        mongoOps.findAllAndRemove(query, Author.class);
        return mongoOps.insert(author);
    }
}
