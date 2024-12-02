package com.library.managment.library_mgmt.controllers;

import com.library.managment.library_mgmt.entities.Author;
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
            return ResponseEntity.ok(service.getAuthorById(id));

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
            return ResponseEntity.ok(service.getAuthorByName(name));

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
            service.addAuthor(author);
            return ResponseEntity.ok().body("Author created.");

        }catch(Exception e){
            return  ResponseEntity.internalServerError().body("An error has occurred.");
        }
    }

    @DeleteMapping("/delete")
    public DeleteResult deleteAuthor(@RequestParam(value = "id") String id){
        return service.deleteAuthor(id);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateAuthor(@RequestBody @Validated Author author, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body("Validation errors found.");
        }
        try{
            service.updateAuthor(author);
            return ResponseEntity.ok().body("Author updated.");

        }catch(Exception e){
            return  ResponseEntity.internalServerError().body("An error has occurred.");
        }
    }
}
