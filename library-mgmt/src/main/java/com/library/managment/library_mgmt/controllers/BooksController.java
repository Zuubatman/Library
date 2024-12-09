package com.library.managment.library_mgmt.controllers;

import com.library.managment.library_mgmt.entities.Book;
import com.library.managment.library_mgmt.service.BooksService;
import com.mongodb.client.result.DeleteResult;
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
    public ResponseEntity<?> getBooksById(@RequestParam(value = "id") String id){
        if(id == null|| id.trim().isEmpty()){
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try {
            return ResponseEntity.ok(service.getBookById(id));

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("An error occoured.");
        }
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
    public ResponseEntity<String> deleteBook(@RequestParam(value = "id") String id){
        if(id.trim().isEmpty() || id == null){
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            var deleted = service.deleteBook(id);
            if(deleted.getDeletedCount() > 0){
                return ResponseEntity.ok().body("Book deleted");
            }
            return ResponseEntity.badRequest().body("Book not found");

        } catch (Exception e){
            return ResponseEntity.internalServerError().body("An error has occurred.");
        }
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
