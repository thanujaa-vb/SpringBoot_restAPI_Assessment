package com.project.FirstJavaApplication.mongodb;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.FirstJavaApplication.model.Book;
import com.project.FirstJavaApplication.model.person;


public interface MongoDB extends MongoRepository<person, String> {

    void save(Book data);

    Optional<person> findByEmail(String email);

}
