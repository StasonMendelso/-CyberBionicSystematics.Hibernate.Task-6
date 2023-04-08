package org.stanislav.relations.repository;

import org.stanislav.relations.entity.Book;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class BookRepository extends Repository {
    public BookRepository() {
        super(HibernateUtil.getSessionFactory());
    }

    public void create(Book book) {
        doInTransaction(session -> {
            session.save(book);
            return null;
        });
    }

    public List<Book> readAll() {
        return doInTransaction(session -> session.createQuery("FROM Book", Book.class).getResultList());
    }
}
