package com.library.managment.library_mgmt.repository;

import com.library.managment.library_mgmt.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksRepository {

    private final MongoOperations mongoOps;

    @Autowired
    public BooksRepository(MongoOperations mongoOperations) {
        this.mongoOps = mongoOperations;
    }

    public List<Book> getAllBooks(){
        return mongoOps.findAll(Book.class);
    }
}
