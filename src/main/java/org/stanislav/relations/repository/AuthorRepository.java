package org.stanislav.relations.repository;

import org.stanislav.relations.entity.Author;

import java.util.List;

/**
 * @author Stanislav Hlova
 */
public class AuthorRepository extends Repository {
    public AuthorRepository() {
        super(HibernateUtil.getSessionFactory());
    }

    public void create(Author author) {
        doInTransaction(session -> {
            session.save(author);
            return null;
        });
    }

    public List<Author> readAll() {
        return doInTransaction(session -> session.createQuery("FROM Author", Author.class).getResultList());
    }
}
