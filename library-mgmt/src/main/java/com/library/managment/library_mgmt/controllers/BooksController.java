package com.library.managment.library_mgmt.controllers;

import com.library.managment.library_mgmt.entities.Book;
import com.library.managment.library_mgmt.service.BooksService;
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
            Book book = service.getBookById(id);
            if(book != null){
                return ResponseEntity.ok().body(book);
            }else{
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("An error occoured.");
        }
    }

    @GetMapping("/getByTitle")
    public ResponseEntity<?> getBooksByTitle(@RequestParam("title") String title){
        if(title == null || title.trim().isEmpty()){
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            List<Book> books = service.getBookByTitle(title);
            if(!books.isEmpty()){
                return ResponseEntity.ok().body(books);
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e ){
            return ResponseEntity.internalServerError().body("An error occoured.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody @Validated Book book, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            Book bookAdded = service.addBook(book);
            if(bookAdded != null){
                return ResponseEntity.ok().body("Book added.");
            } else{
                return ResponseEntity.internalServerError().body("An error has occurred.");
            }

        }catch(Exception e){
            return  ResponseEntity.internalServerError().body("An error has occurred.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBook(@RequestParam(value = "id") String id){
        if(id == null || id.trim().isEmpty()){
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            var deleted = service.deleteBook(id);
            if(deleted.getDeletedCount() > 0){
                return ResponseEntity.ok().body("Book deleted.");
            }
            return ResponseEntity.notFound().build();

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
            var update = service.updateBook(book);
            if(update.getModifiedCount() > 0 ){
                return ResponseEntity.ok().body("Book updated.");
            } else {
                return ResponseEntity.notFound().build();
            }

        }catch(Exception e){
           return  ResponseEntity.internalServerError().body("An error has occurred.");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByTitleOrAuthor(@RequestParam(value = "name") String name){
        if(name == null || name.trim().isEmpty()){
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            List<Book> books = service.searchByTitleOrAuthor(name);
            if(!books.isEmpty()){
                return ResponseEntity.ok().body(books);
            }
            return ResponseEntity.notFound().build();

        }catch (Exception e){
            return  ResponseEntity.internalServerError().body("An error has occurred.");
        }
    }
}
