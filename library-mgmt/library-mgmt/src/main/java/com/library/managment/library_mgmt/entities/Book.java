package com.library.managment.library_mgmt.entities;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
@Document(collection = "books")
public class Book {

    @Getter
    private String id;
    @Getter
    @NonNull
    private String title;
    @Getter
    @NonNull
    private String isbn;
    @Getter
    @NonNull
    private String publicationYear;
    @Getter
    @NonNull
    private String authorId;

    public Book(@NonNull String title, @NonNull String isbn, @NonNull String publicationYear, @NonNull String authorId) {
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
