package com.project.FirstJavaApplication;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;

import com.project.FirstJavaApplication.model.person;
import com.project.FirstJavaApplication.service.PersonService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class personTest {
    static String itemId;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    PersonService personService;


    @Test
    @Order(1)
    void testPostItem() {
        person newItem = new person();
        newItem.setFirstName("test user");
        newItem.setLastName("test last name");
        newItem.setEmail("test@gmail.com");
        newItem.setPassword("password");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<person> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<person> response = restTemplate.postForEntity("/person/", request, person.class);
        itemId = response.getBody().getId();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(2)
    void testGetItemById() {
        ResponseEntity<person> response = restTemplate.exchange("/person/" + itemId, HttpMethod.GET, null,
                new ParameterizedTypeReference<person>() {
                });
        // person items = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    @Order(3)
    public void test_getAllData() throws Exception {
        ResponseEntity<List<person>> response = restTemplate.exchange("/person/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<person>>() {
                });
        System.out.println("======================================================"+response);
        // List<person> items = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        // assertThat(items.size()).isEqualTo(3);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @Order(4)
    void testUpdateItem() {
        person newItem = new person();
        newItem.setFirstName("test user");
        newItem.setLastName("test last name");
        newItem.setEmail("test@gmail.com");
        newItem.setPassword("newpassword");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<person> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<String> response = restTemplate.exchange("/person/" + itemId, HttpMethod.PUT, request,
                new ParameterizedTypeReference<String>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).isEqualTo("Data Updated Successfully");

    }
    @Test
    @Order(5)
    void testDeleteItem() {
        ResponseEntity<String> response = restTemplate.exchange("/person/" + itemId, HttpMethod.DELETE, null,
                new ParameterizedTypeReference<String>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).isEqualTo("successfully deleted");

    }
	@Test
    void testPostItemWrongURL() {
        person newItem = new person();
        newItem.setFirstName("test user");
        newItem.setLastName("test last name");
        newItem.setEmail("test1@gmail.com");
        newItem.setPassword("newpassword");


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<person> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<person> response = restTemplate.postForEntity("/persons", request, person.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testPostItemWithNoEmail() {
        person newItem = new person();
        newItem.setFirstName("test user");
        newItem.setLastName("test last name");
        newItem.setPassword("newpassword");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<person> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<person> response = restTemplate.postForEntity("/person/", request, person.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    // @Order(6)
    void testUpdateItemWithNoItemWithId() {
        person newItem = new person();
        newItem.setFirstName("test user");
        newItem.setLastName("test last name");
        newItem.setEmail("test2@gmail.com");
        newItem.setPassword("newpassword");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<person> request = new HttpEntity<>(newItem, headers);
        ResponseEntity<String> response = restTemplate.exchange("/person/" + itemId+1, HttpMethod.PUT, request,
                new ParameterizedTypeReference<String>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertThat(response.getBody()).isEqualTo("No Data with givn Id");
    }
    @Test
    // @Order(6)
    void testGetItemWithWrongId() {
        ResponseEntity<person> response = restTemplate.exchange("/person/" + itemId+1, HttpMethod.GET, null,
                new ParameterizedTypeReference<person>() {
                });
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

}
