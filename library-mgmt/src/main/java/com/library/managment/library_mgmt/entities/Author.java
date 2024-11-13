package com.library.managment.library_mgmt.entities;

import lombok.Getter;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "authors")
public class Author {

    @Getter
    private String id;
    @Getter
    private String name;
    @Getter
    private String biography;
    @Getter
    private String birthDate;

    public Author(String name, String biography, String birthDate){
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
    }
}
