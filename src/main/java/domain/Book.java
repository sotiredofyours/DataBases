package domain;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
@DynamicUpdate
public class Book {

    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    private long id;

    @Basic(fetch = FetchType.LAZY)
    private String title;

    @Basic
    private String description;

    @Basic(fetch = FetchType.LAZY)
    private int pages;

    @Basic(fetch = FetchType.LAZY)
    private int year;


    @ManyToMany(mappedBy = "books")
    private List<Author> author;

    public Book() {

    }

    public long getId() {
        return id;
    }

    public void addAuthor(Author author){
        getAuthor().add(author);
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public Book(String title, String description, int pages, int year) {
        this.title = title;
        this.description = description;
        this.pages = pages;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + description + '\'' +
                ", pages=" + pages +
                ", year=" + year +
                '}';
    }

    public int getYear() {
        return year;
    }

}