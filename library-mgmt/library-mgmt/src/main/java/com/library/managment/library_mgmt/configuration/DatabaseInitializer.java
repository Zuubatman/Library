package com.library.managment.library_mgmt.configuration;

import com.library.managment.library_mgmt.entities.Author;
import com.library.managment.library_mgmt.entities.Book;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.MongoOperations;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    final private MongoOperations mongoOperations;

    @Autowired
    public DatabaseInitializer(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void run(String... args) throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (!mongoOperations.collectionExists("authors") &&
                !mongoOperations.collectionExists("books")) {

            Author Shakespeare = new Author("William Shakespeare", "William Shakespeare was an English poet, playwright, and actor, widely regarded as the greatest playwright of all time. His works include iconic plays such as \"Hamlet,\" \"Romeo and Juliet,\" and \"Macbeth.\" Shakespeare profoundly influenced the English language and world literature.", "26/04/1564");
            Author Lispector = new Author("Clarice Lispector", "Clarice Lispector was a significant Brazilian writer born in Ukraine. Known for her innovative and introspective prose, her works explore existentialism and human psychology. Among her most famous books are:  The Hour of the Star, Near to the Wild Heart, and The Passion According to G.H.","10/12/1920");
            Author Orwell = new Author("George Orwell", "George Orwell, born Eric Arthur Blair, was an English novelist, essayist, and critic. He is best known for his dystopian novels \"1984\" and \"Animal Farm,\" which critique totalitarian regimes and explore themes of surveillance, propaganda, and the abuse of power. Orwell's works have had a profound impact on political thought and culture.", "25/05/1903");

            mongoOperations.createCollection("authors");
            mongoOperations.insert(Shakespeare, "authors");
            mongoOperations.insert(Lispector, "authors");
            mongoOperations.insert(Orwell, "authors");
            mongoOperations.createCollection("books");

            mongoOperations.insert(new Book("1984", "978-0-9999999-1-8", "1949", Orwell.getId()), "books");
            mongoOperations.insert(new Book("Animal Farm", "978-0-9999999-2-5", "1945", Orwell.getId()), "books");
            mongoOperations.insert(new Book("The Hour of the Star", "978-0-8888888-1-4", "1977", Lispector.getId()), "books");
            mongoOperations.insert(new Book("Hamlet", "978-0-7777777-1-6", "1603", Shakespeare.getId()), "books");
            mongoOperations.insert(new Book("Romeo and Juliet", "978-0-7777777-2-3", "1597", Shakespeare.getId()), "books");
            mongoOperations.insert(new Book("Macbeth", "978-0-7777777-3-0", "1623", Shakespeare.getId()), "books");
        }
    }
}
