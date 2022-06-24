package domain;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Table(name = "publisher")
@Entity
public class Publisher {

    public Publisher() {
    }

    public Publisher( String name, String city) {
        this.name = name;
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }



    public void addBook(Book book){
        this.books.add(book);
    }

    public long getId() {
        return id;
    }


    public List<Book> getBooks() {
        return books;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private long id;

    @Basic(fetch = FetchType.LAZY)
    private String name;

    @Basic(fetch = FetchType.LAZY)
    private String city;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "publisher_id")
    private List<Book> books = new ArrayList<>();

}
