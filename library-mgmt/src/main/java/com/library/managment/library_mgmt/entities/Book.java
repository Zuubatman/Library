package com.library.managment.library_mgmt.entities;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "books")
public class Book {
    @Getter
    private String id;
    @Getter
    private String title;
    @Getter
    private String isbn;
    @Getter
    private String publicationYear;
    @Getter
    private String authorId;

    public Book(String title, String isbn, String publicationYear, String authorId) {
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", isbn=" + isbn +
                ", publicationYear=" + publicationYear + ", authorId=" + authorId +"]";
    }
}
