package com.library.managment.library_mgmt.service;

import com.library.managment.library_mgmt.entities.Author;
import com.library.managment.library_mgmt.repository.AuthorsRepository;
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
}
