package com.library.managment.library_mgmt.repository;

import com.library.managment.library_mgmt.entities.Author;
import com.library.managment.library_mgmt.entities.Book;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BooksRepository {

    final private MongoOperations mongoOps;

    @Autowired
    public BooksRepository(MongoOperations mongoOperations) {
        this.mongoOps = mongoOperations;
    }

    public List<Book> getAllBooks(){
        return mongoOps.findAll(Book.class);
    }

    public Book getBookById(String id){
        return mongoOps.findById(id, Book.class);
    }

    public List<Book> getBooksByTitle(String title){
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));
        return mongoOps.find(query, Book.class);
    }

    public Book addBook(Book book){
        return mongoOps.insert(book);
    }

    public DeleteResult deleteBook(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoOps.remove(query, Book.class);
    }

    public UpdateResult updateBook(Book book){
        Query query = new Query();
        query.addCriteria(Criteria.where("isbn").is(book.getIsbn()));

        Update update = new Update();
        update.set("title", book.getTitle());
        update.set("isbn", book.getIsbn());
        update.set("publicationYear", book.getPublicationYear());
        update.set("authorId", book.getAuthorId());

        return mongoOps.updateFirst(query, update, Book.class);

    }

    public List<Book> searchByTitleOrAuthor(String name){
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("name").is(name));
        List<Author> authors = mongoOps.find(query1, Author.class);

        List<String> authorIds = authors.stream()
                .map(Author::getId)
                .toList();

        Query query2 = new Query();
        query2.addCriteria(Criteria.where("authorId").in(authorIds));
        List<Book> books = mongoOps.find(query2, Book.class);

        Query query3 = new Query();
        query3.addCriteria(Criteria.where("title").is(name));
        books.addAll(mongoOps.find(query3, Book.class));

        return books;

    }
}
