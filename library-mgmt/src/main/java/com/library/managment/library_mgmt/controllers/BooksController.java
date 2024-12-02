package com.library.managment.library_mgmt.controllers;

import com.library.managment.library_mgmt.entities.Book;
import com.library.managment.library_mgmt.service.BooksService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public Book getBookById(@RequestParam(value = "id") String id){
        return service.getBookById(id);
    }

    @GetMapping("/getByTitle")
    public List<Book> getBooksByTitle(@RequestParam("title") String title){
        return service.getBookByTitle(title);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody @Validated Book book, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            service.addBook(book);
            return ResponseEntity.ok().body("Book created.");

        }catch(Exception e){
            return  ResponseEntity.internalServerError().body("An error has occurred.");
        }
    }

    @DeleteMapping("/delete")
    public DeleteResult deleteBook(@RequestParam(value = "id") String id){
       return service.deleteBook(id);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody @Validated Book book, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            service.updateBook(book);
            return ResponseEntity.ok().body("Book updated");

        }catch(Exception e){
           return  ResponseEntity.internalServerError().body("An error has occurred.");
        }
    }

    @GetMapping("/search")
    public List<Book> searchByTitleOrAuthor(@RequestParam(value = "name") String name){
        return service.searchByTitleOrAuthor(name);
    }
}
