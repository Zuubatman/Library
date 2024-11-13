package com.library.managment.library_mgmt.service;

import com.library.managment.library_mgmt.entities.Author;
import com.library.managment.library_mgmt.repository.AuthorsRepository;
import com.mongodb.client.result.DeleteResult;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorsService {

    final private AuthorsRepository repository;

    public AuthorsService(AuthorsRepository _repository){
        this.repository = _repository;
    }

    public List<Author> getAllAuthors(){
        return repository.getAllAuthors();
    }

    public Author getAuthorById(String id){
        return repository.getAuthorById(id);
    }

    public List<Author> getAuthorByName(String name){
        return repository.getAuthorByName(name);
    }

    public Author addAuthor(Author author){
        return repository.addAuthor(author);
    }

    public DeleteResult deleteAuthor(String id){
        return repository.deleteAuthor(id);
    }

    public Author updateAuthor(Author author){
        return repository.updateAuthor(author);
    }

}
