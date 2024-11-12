package com.library.managment.library_mgmt.service;

import com.library.managment.library_mgmt.entities.Book;
import com.library.managment.library_mgmt.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    private final BooksRepository repository;

    public BooksService(BooksRepository _repository){
        this.repository = _repository;
    }

    public List<Book> getAllBooks(){
        return repository.getAllBooks();
    }
}
