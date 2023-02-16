package com.project.FirstJavaApplication.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.FirstJavaApplication.model.person;

public interface PersonService {
    ResponseEntity<person> createUser(person p);

    ResponseEntity<String> updateUser(person p, String userId);

    List<person> getAllData();

    ResponseEntity<person> getUserById(String userId);

    ResponseEntity<String> deleteUser(String userId);
}
