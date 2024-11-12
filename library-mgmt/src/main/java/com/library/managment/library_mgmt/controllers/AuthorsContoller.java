package com.library.managment.library_mgmt.controllers;

import com.library.managment.library_mgmt.entities.Author;
import com.library.managment.library_mgmt.service.AuthorsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
