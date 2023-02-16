package com.project.FirstJavaApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;


import com.project.FirstJavaApplication.model.Book;
import com.project.FirstJavaApplication.service.BookService;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class bookTest {
    static String itemId;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    BookService bookService;

    @Test
    @Order(1)
    void testPostBookItem() {
        Book newItem = new Book();
        newItem.setName("NewBook");
        newItem.setAuthor("newAuthor");
        newItem.setCategory("Comic");
        newItem.setCopies(100);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Book> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<Book> response = restTemplate.postForEntity("/book/", request, Book.class);
        itemId = response.getBody().getId();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(2)
    void testGetBookItemById() {
        ResponseEntity<Book> response = restTemplate.exchange("/book/" + itemId, HttpMethod.GET, null,
                new ParameterizedTypeReference<Book>() {
                });
        // person items = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    @Order(3)
    public void test_getAllBookData() throws Exception {
        ResponseEntity<List<Book>> response = restTemplate.exchange("/book/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>() {
                });
        // List<Book> items = (List<Book>) response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // assertThat(items.size()).isEqualTo(3);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(4)
    void testUpdateBookItem() {
        Book newItem = new Book();
        newItem.setName("Updated Book Name");
        newItem.setAuthor("newAuthor");
        newItem.setCategory("Comic");
        newItem.setCopies(100);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Book> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<String> response = restTemplate.exchange("/book/" + itemId, HttpMethod.PUT, request,
                new ParameterizedTypeReference<String>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).isEqualTo("Data Updated Successfully");

    }
    @Test
    @Order(5)
    void testDeleteBookItem() {
        ResponseEntity<String> response = restTemplate.exchange("/book/" + itemId, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<String>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).isEqualTo("successfully deleted");

}
}
