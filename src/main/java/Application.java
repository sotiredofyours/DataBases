import dao.BookDao;
import dao.Dao;
import domain.Author;
import domain.Book;
import domain.Publisher;

import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.util.ArrayList;

public class Application {
    private static Dao<Book> jpaBookDao;
    public static void main(String[] args) {

      var factory = new Configuration().configure().buildSessionFactory();
      var session = factory.openSession();
      var entityManager = factory.createEntityManager();
      var bookDao = new BookDao(entityManager);
       jpaBookDao = bookDao;
       var list = jpaBookDao.getAll();
      var titles = new ArrayList<String>();
      for (var x :
               list) {
          titles.add(x.getTitle());
      };
        for (var x:
             titles) {
            System.out.println(x);
        }
    }
}
