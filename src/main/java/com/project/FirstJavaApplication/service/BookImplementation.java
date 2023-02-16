package com.project.FirstJavaApplication.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.project.FirstJavaApplication.model.Book;
import com.project.FirstJavaApplication.mongodb.BookRepo;

@Service
public class BookImplementation implements BookService {
    @Autowired
    BookRepo mongoDB;

    @Override
    public ResponseEntity<Book> createBook(Book data) {
        try {
            this.mongoDB.save(data);
            return ResponseEntity.status(HttpStatus.OK).body(data);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public String updateBook(Book data, String bookId) {
        try {
            Optional<Book> record = mongoDB.findById(bookId);
            if (record.isPresent()) {
                Book Data = (Book) record.get();
                Data.setName(data.getName());
                Data.setAuthor(data.getAuthor());
                Data.setCopies(data.getCopies());
                mongoDB.save(Data);
                return "Data Updated Successfully";
            } else {
                return "No Data with givn Id";
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public List<Book> getAllData() {
        return this.mongoDB.findAll();
    }

    @Override
    public ResponseEntity<Book> getBookById(String bookId) {
        try {
            Optional<Book> productDb = this.mongoDB.findById(bookId);
            if (productDb.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(productDb.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productDb.get());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Override
    public ResponseEntity<String> deleteBook(String bookId) {
        try {
            Optional<Book> productDb = this.mongoDB.findById(bookId);
            if (productDb.isPresent()) {
                this.mongoDB.deleteById(bookId);
                return ResponseEntity.status(HttpStatus.OK).body("successfully deleted");
            } else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Record Found");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Book> getAllByAuthor(String author, int quantity1, int quantity2) {
        MatchOperation matchOperation = Aggregation.match(Criteria.where("Author").is(author)
                .andOperator(Criteria.where("Copies").gte(quantity1), Criteria.where("Copies").lte(quantity2)));
        SortOperation sortByPopDesc = Aggregation.sort(Sort.by(Direction.DESC, "itemName"));
        Aggregation aggregation = Aggregation.newAggregation(matchOperation, sortByPopDesc);
        AggregationResults<Book> results = mongoTemplate.aggregate(aggregation, "Books", Book.class);
        return results.getMappedResults();
    }

}
