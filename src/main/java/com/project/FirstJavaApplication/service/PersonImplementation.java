package com.project.FirstJavaApplication.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.FirstJavaApplication.model.person;
import com.project.FirstJavaApplication.mongodb.MongoDB;
@Service
public class PersonImplementation implements PersonService {
    @Autowired
    MongoDB mongoDB; 
    
    @Override
    public ResponseEntity<person> createUser(person data) {
        try{
            String email = data.getEmail();
            Optional<person> record = mongoDB.findByEmail(email);
            if(record.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(record.get());
            }
            else{
        this.mongoDB.save(data);
            return ResponseEntity.status(HttpStatus.OK).body(data);
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<String> updateUser(person data, String userId) {
        try {
            Optional<person> record = mongoDB.findById(userId);
            if (record.isPresent()) {
                person Data = (person) record.get();
                Data.setFirstName(data.getFirstName());
                Data.setLastName(data.getLastName());
                Data.setEmail(data.getEmail());
                Data.setPassword(data.getPassword());
                mongoDB.save(Data);
                return ResponseEntity.status(HttpStatus.OK).body("Data Updated Successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Data with givn Id");
                }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }  
      }

    @Override
    public List<person> getAllData() {    
        return this.mongoDB.findAll();
    }

    @Override
    public ResponseEntity<person> getUserById(String userId) {
        try{
            Optional < person > productDb = this.mongoDB.findById(userId);
            if (productDb.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(productDb.get());
            }  
            else{
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productDb.get());
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            
        }
     }
    @Override
    public ResponseEntity< String > deleteUser(String userId) {
        try{
            Optional < person > productDb = this.mongoDB.findById(userId);
            if (productDb.isPresent()) {
            this.mongoDB.deleteById(userId);
            return ResponseEntity.status(HttpStatus.OK).body("successfully deleted");
            }
            else return ResponseEntity.status(HttpStatus.OK).body("No Record Found");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    
    
}
