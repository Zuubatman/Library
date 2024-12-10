package com.library.managment.library_mgmt.controllers;

import com.library.managment.library_mgmt.entities.Author;
import com.library.managment.library_mgmt.entities.Book;
import com.library.managment.library_mgmt.service.AuthorsService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsContoller {
    final private AuthorsService service;

    public AuthorsContoller(AuthorsService _service){
        this.service = _service;
    }

    @GetMapping()
    public List<Author> getAllAuthors(){
        return service.getAllAuthors();
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getAuthorById(@RequestParam(value = "id") String id){
        if(id == null|| id.trim().isEmpty()){
            return ResponseEntity.badRequest().body("Invalid id.");
        }
        try {
            Author author = service.getAuthorById(id);
            if(author != null){
                return ResponseEntity.ok().body(author);
            }else{
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("An error occoured.");
        }
    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getAuthorByName(@RequestParam(value = "name") String name){
        if(name == null|| name.trim().isEmpty()){
            return ResponseEntity.badRequest().body("Invalid name.");
        }
        try {
            List<Author> author = service.getAuthorByName(name);
            if(!author.isEmpty()){
                return ResponseEntity.ok().body(author);
            }else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.internalServerError().body("An error occoured.");
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestBody @Validated Author author, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            Author authorAdded = service.addAuthor(author);
            if(authorAdded != null){
                return ResponseEntity.ok().body("Author added.");
            } else{
                return ResponseEntity.internalServerError().body("An error has occurred.");
            }

        }catch(Exception e){
            return  ResponseEntity.internalServerError().body("An error has occurred.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAuthor(@RequestParam(value = "id") String id){
        if(id == null || id.trim().isEmpty()){
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            var deleted = service.deleteAuthor(id);
            if(deleted.getDeletedCount() > 0){
                return ResponseEntity.ok().body("Author deleted");
            }
            return ResponseEntity.notFound().build();

        } catch (Exception e){
            return ResponseEntity.internalServerError().body("An error has occurred.");
        }
    }
    @PatchMapping("/update")
    public ResponseEntity<String> updateAuthor(@RequestBody @Validated Author author, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            var update = service.updateAuthor(author);
            if(update.getModifiedCount() > 0 ){
                return ResponseEntity.ok().body("Author updated.");
            } else {
                return ResponseEntity.notFound().build();
            }

        }catch(Exception e){
            return  ResponseEntity.internalServerError().body("An error has occurred.");
        }
    }
}
