package org.stanislav.ex_002_select_where.repository;


import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.stanislav.ex_002_select_where.entity.Author;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class AuthorRepository extends Repository {

    public AuthorRepository() {
        super(HibernateUtil.getSessionFactory());
    }

    public Author addAuthor(Author author) {
        doInTransaction(session -> session.save(author));
        return author;
    }

    public List<Author> getAuthorList() {
        return doInTransaction(session -> {
            // объект-конструктор запросов для Criteria API
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Author.class);
            Root<Author> root = criteriaQuery.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)
            criteriaQuery.select(root);// необязательный оператор, если просто нужно получить все значения
            //этап выполнения запроса
            Query query = session.createQuery(criteriaQuery);

            return (List<Author>) query.getResultList();
        });
    }

    public Author getAuthorById(long id) {
        return doInTransaction(session -> session.get(Author.class, id));
    }

    public boolean updateAuthorsNameWhereSizeOfLastnameBiggerThan(String newName, int length) {
        return doInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaUpdate<Author> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Author.class);

            Root<Author> root = criteriaUpdate.from(Author.class);
            criteriaUpdate.where(criteriaBuilder.greaterThan(criteriaBuilder.length(root.get("lastName")), length));
            criteriaUpdate.set("name", newName);

            return session.createQuery(criteriaUpdate).executeUpdate() > 0;
        });
    }

}
