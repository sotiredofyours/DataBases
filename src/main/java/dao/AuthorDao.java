package dao;

import domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class AuthorDao implements Dao<Author> {

    private EntityManager entityManager;

    @Override
    public Optional<Author> get(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }

    @Override
    public List<Author> getAll() {
        var query = entityManager.createQuery("select e from Author e");
        return query.getResultList();
    }

    @Override
    public void save(Author author) {
        executeInsideTransaction(entityManager -> entityManager.persist(author));
    }

    @Override
    public void update(Author author, String[] params) {
        author.setName(Objects.requireNonNull(params[0], "Name of author can`t be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(author));
    }

    @Override
    public void delete(Author author) {
        executeInsideTransaction(entityManager -> entityManager.remove(author));
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
