package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Basic
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    @ManyToMany
    private List<Book> books = new ArrayList<>();

    public Author(){

    }

    public Author(String name) {
        this.name = name;
        this.books = books;
    }
}
