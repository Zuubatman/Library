package com.library.managment.library_mgmt.controllers;

import com.library.managment.library_mgmt.entities.Author;
import com.library.managment.library_mgmt.service.AuthorsService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
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
    public Author getAuthorById(@RequestParam(value = "authorId")String id){
        return service.getAuthorById(id);
    }

    @GetMapping("/getByName")
    public List<Author> getAuthorByName(@RequestParam(value = "name") String name){
        return service.getAuthorByName(name);
    }

    @GetMapping("/add")
    public Author addAuthor(@RequestBody Author author){
        return service.addAuthor(author);
    }

    @GetMapping("/delete")
    public DeleteResult deleteAuthor(@RequestParam(value = "id") String id){
        return service.deleteAuthor(id);
    }

    @GetMapping("/update")
    public UpdateResult updateAuthor(@RequestBody Author author){
        return service.updateAuthor(author);
    }
}
