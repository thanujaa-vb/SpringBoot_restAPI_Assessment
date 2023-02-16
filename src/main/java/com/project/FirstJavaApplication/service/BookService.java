package com.project.FirstJavaApplication.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.FirstJavaApplication.model.Book;

public interface BookService {
    ResponseEntity<Book> createBook(Book data);

    String updateBook(Book data, String bookId);

    List<Book> getAllData();

    ResponseEntity<Book> getBookById(String bookId);

    ResponseEntity<String> deleteBook(String bookId);

    List<Book> getAllByAuthor(String author, int quantity1, int quantity2);

}
