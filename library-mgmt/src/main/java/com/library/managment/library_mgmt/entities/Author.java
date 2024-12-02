package com.library.managment.library_mgmt.entities;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Document(collection = "authors")
public class Author {

    @Getter
    private String id;
    @Getter
    @NonNull
    private String name;
    @Getter
    @NonNull
    private String biography;
    @Getter
    @NonNull
    private String birthDate;

    public Author(@NonNull String name, @NonNull String biography, @NonNull String birthDate){
        this.name = name;
        this.biography = biography;
        this.birthDate = birthDate;
    }
}
