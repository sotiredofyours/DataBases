package dao;

import domain.Publisher;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public class PublisherDao implements Dao<Publisher> {

    private EntityManager entityManager;

    @Override
    public Optional<Publisher> get(long id) {
        return Optional.ofNullable(entityManager.find(Publisher.class, id));
    }

    @Override
    public List<Publisher> getAll() {
        var query = entityManager.createQuery("select e from Publisher e");
        return query.getResultList();
    }

    @Override
    public void save(Publisher publisher) {
        executeInsideTransaction(entityManager -> entityManager.persist(publisher));
    }

    @Override
    public void update(Publisher publisher, String[] params) {
        publisher.setName(Objects.requireNonNull(params[0], "Name of publisher can`t be null"));
        publisher.setName(Objects.requireNonNull(params[1], "City of publisher can`t be null"));
        executeInsideTransaction(entityManager -> entityManager.merge(publisher));
    }

    @Override
    public void delete(Publisher publisher) {
        executeInsideTransaction(entityManager -> entityManager.remove(publisher));
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

