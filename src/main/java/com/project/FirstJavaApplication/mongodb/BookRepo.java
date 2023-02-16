package com.project.FirstJavaApplication.mongodb;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.project.FirstJavaApplication.model.Book;

public interface BookRepo extends MongoRepository<Book, String> {
    
}  
 
 