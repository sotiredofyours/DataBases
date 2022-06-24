package dao;

import domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class BookDao implements Dao<Book>{

    private EntityManager entityManager;

    public BookDao(){}
    public BookDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Book> get(long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Book e");
        return query.getResultList();
    }

    @Override
    public void save(Book book) {
        executeInsideTransaction(entityManager -> entityManager.persist(book));
    }

    @Override
    public void update(Book book, String[] params) {
        book.setTitle(Objects.requireNonNull(params[0], "Title cannot be null"));
        book.setDescription(Objects.requireNonNull(params[1], "Text cannot be null"));
        if (Integer.getInteger(params[2])<= 0) throw new RuntimeException("Pages must be more then 0");
        book.setPages(Integer.getInteger(params[2]));
        if (Integer.getInteger(params[3])<= 0) throw new RuntimeException("Incorrect error");
        book.setPages(Integer.getInteger(params[3]));
        executeInsideTransaction(entityManager -> entityManager.merge(book));
    }

    @Override
    public void delete(Book book) {
        executeInsideTransaction(entityManager -> entityManager.remove(book));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit();
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
