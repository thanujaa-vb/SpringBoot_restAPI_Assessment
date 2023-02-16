package com.project.FirstJavaApplication.model;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Books")
public class Book {
    @Id
    private String id;
    @NotBlank(message = "Book Name must be provided")
    private String Name;
    @NotBlank(message = "Book Author must be provided")
    private String Author;
    @NotBlank(message = "Book Category must be provided")
    private String Category;
    @Min(1)
    @NotNull(message = "Copies need to be given")
    private int Copies;
 
    public Book(){
        
    }
    public Book(String Name, String Author, String Category, int Copies)
    {
        this.Name = Name;
        this.Author = Author;
        this.Category = Category;
        this.Copies = Copies;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getCopies() {
        return Copies;
    }

    public void setCopies(int copies) {
        Copies = copies;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

}
