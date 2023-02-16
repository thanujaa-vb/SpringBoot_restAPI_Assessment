package com.project.FirstJavaApplication.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.FirstJavaApplication.model.Book;
import com.project.FirstJavaApplication.service.BookService;


@RestController
@ControllerAdvice
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

   
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Book> createBook(@RequestBody Book data) {
        return bookService.createBook(data);
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.PUT)
    public String updateBook(@RequestBody Book data, @PathVariable String bookId) {
        return bookService.updateBook(data, bookId);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Book> getAll() {
        return bookService.getAllData();
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable String bookId){
        return bookService.getBookById(bookId);
    }

    @RequestMapping(path = "/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBook(@PathVariable String bookId) {
        return bookService.deleteBook(bookId);
    }
    
    @RequestMapping(value = "/aggregation", method = RequestMethod.GET)
    public List<Book> getAllAggregate(
        @RequestParam(required = false) String author,
        @RequestParam(required = false) int quantity1,
         @RequestParam(required = false) int quantity2
         ) {
            List<Book> result =  bookService.getAllByAuthor(author, quantity1, quantity2);
        return result;
    }

}
