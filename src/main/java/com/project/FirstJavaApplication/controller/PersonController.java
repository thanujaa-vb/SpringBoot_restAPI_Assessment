package com.project.FirstJavaApplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.FirstJavaApplication.model.person;
import com.project.FirstJavaApplication.service.PersonService;

@RestController
@ControllerAdvice
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<person> createUser(@Valid @RequestBody person data) {
        return personService.createUser(data);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateUser(@RequestBody person data, @PathVariable String userId) {
        return personService.updateUser(data, userId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<person> getAllProduct() {
        return personService.getAllData();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<person> getUserById(@PathVariable String userId){
        return personService.getUserById(userId);
    }

    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        return personService.deleteUser(userId);
    }
    
}
