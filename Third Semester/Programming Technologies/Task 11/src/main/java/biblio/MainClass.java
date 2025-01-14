package biblio;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Writer;
import java.util.List;

public class MainClass {
    // пишем тут те таблицы которые мы создаём с помощью гибернета
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Author.class)
                .addAnnotatedClass(Reader.class)
                .addAnnotatedClass(Catalog.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            // Создание автора:
//            session = factory.getCurrentSession();
//            Author author = new Author();
//            //author.setName("Bruce Eckel");
//            author.setName("Rowling");
//            session.beginTransaction();
//            session.save(author);
//            session.getTransaction().commit();

            // Создание каталога:
//            session = factory.getCurrentSession();
//            Catalog catalog = new Catalog();
//            catalog.setTitle("Fantasy #17");
//            session.beginTransaction();
//            session.save(catalog);
//            session.getTransaction().commit();

            // Меняем имя каталога (как в sql update для таблицы):
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Catalog catalog = session.get(Catalog.class, 2L);
//            catalog.setTitle("Fantasy #8 new");
//            //session.save(catalog);  // можно без save т.к. мы обновляем, ничего нового не создаём
//            session.getTransaction().commit();
//            System.out.println(catalog);

            // Создаём связку в таблице books_readers
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Reader reader = session.get(Reader.class, 1);
//            Book book = session.get(Book.class, 2);
//            reader.getBooks().add(book);
//            //session.save(catalog);  // можно без save т.к. мы обновляем, ничего нового не создаём
//            session.getTransaction().commit();

            // HQL запрос:
            session = factory.getCurrentSession();
            session.beginTransaction();
            // в HQL надо брать класс Book
            List<Book> allBooks = session.createQuery("from Book b where b.title = 'Harry Poter 1' or b.author.name = 'Rowling'").getResultList();
            System.out.println(allBooks);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println("error" + e.getMessage());
        } finally {
            factory.close();
            session.close();
        }
    }
}
