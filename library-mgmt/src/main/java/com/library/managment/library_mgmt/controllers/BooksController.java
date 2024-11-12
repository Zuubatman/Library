package com.library.managment.library_mgmt.controllers;

import com.library.managment.library_mgmt.entities.Book;
import com.library.managment.library_mgmt.service.BooksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private BooksService service;

    public BooksController(BooksService _service){
        this.service = _service;
    }

    @GetMapping()
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

//    http://localhost:8080/hello?name=Joao
//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }
//
//    public void addBook(){
//
//    }
}
