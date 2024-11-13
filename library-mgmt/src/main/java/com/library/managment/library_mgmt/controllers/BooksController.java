package com.library.managment.library_mgmt.controllers;

import com.library.managment.library_mgmt.entities.Book;
import com.library.managment.library_mgmt.service.BooksService;
import com.mongodb.client.result.DeleteResult;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    final private BooksService service;

    public BooksController(BooksService _service){
        this.service = _service;
    }

    @GetMapping()
    public List<Book> getAllBooks() {
        return service.getAllBooks();
    }

    @GetMapping("/getById")
    public Book getBookById(@RequestParam(value = "bookId") String id){
        return service.getBookById(id);
    }

    @GetMapping("/getByTitle")
    public List<Book> getBooksByTitle(@RequestParam( "title") String title){
        return service.getBookByTitle(title);
    }

    @GetMapping("/add")
    public Book addBook(@RequestBody Book book){
        return service.addBook(book);
    }

    @GetMapping("/delete")
    public DeleteResult deleteBook(@RequestParam(value = "id") String id){
       return service.deleteBook(id);
    }

    @GetMapping("/update")
    public Book updateBook(@RequestBody Book book){
        return service.updateBook(book);
    }

//    http://localhost:8080/hello?name=Joao
//    @GetMapping("/hello")
//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }
}
