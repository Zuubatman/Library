package com.library.managment.library_mgmt.service;

import com.library.managment.library_mgmt.entities.Book;
import com.library.managment.library_mgmt.repository.BooksRepository;
import com.mongodb.client.result.DeleteResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

    final private BooksRepository repository;

    public BooksService(BooksRepository _repository){
        this.repository = _repository;
    }

    public List<Book> getAllBooks(){
        return repository.getAllBooks();
    }

    public Book getBookById(String id){
        return repository.getBookById(id);
    }

    public List<Book> getBookByTitle(String title){
        return repository.getBooksByTitle(title);
    }

    public void addBook(Book book){
        repository.addBook(book);
    }

    public DeleteResult deleteBook(String id){
        return repository.deleteBook(id);
    }

    public void updateBook(Book book){
        repository.updateBook(book);
    }

    public List<Book> searchByTitleOrAuthor(String name){
        return repository.searchByTitleOrAuthor(name);
    }
}