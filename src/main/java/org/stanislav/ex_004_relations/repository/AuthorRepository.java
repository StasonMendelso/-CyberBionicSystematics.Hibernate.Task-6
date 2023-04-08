package org.stanislav.ex_004_relations.repository;


import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.stanislav.ex_004_relations.entity.Author;

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
            CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
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
    public void deleteById(long id) {
        doInTransaction(session -> {
            Author author = session.get(Author.class, id);
            session.delete(author); // сгенерит ID и вставит в объект
            return null;
        });
    }

    public void deleteCriteria()  {
        doInTransaction(session -> {
            // объект-конструктор запросов для Criteria API
            CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
            CriteriaDelete<org.stanislav.ex_004_relations.entity.Author> cd = cb.createCriteriaDelete(org.stanislav.ex_004_relations.entity.Author.class);
            Root<org.stanislav.ex_004_relations.entity.Author> root = cd.from(org.stanislav.ex_004_relations.entity.Author.class);// первостепенный, корневой entity (в sql запросе - from)
            cd.where(cb.like(root.<String>get("name"), "%1%"));
            //этап выполнения запроса
            Query query = session.createQuery(cd);
            query.executeUpdate();

            return null;
        });

    }

    public void deleteCriteriaLogic()  {
        doInTransaction(session -> {
            CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

            CriteriaDelete<Author> cd = cb.createCriteriaDelete(Author.class);

            Root<Author> root = cd.from(Author.class);
            cd.where(cb.or(
                    cb.and(
                            cb.like(root.<String>get("name"), "%author%"),
                            cb.like(root.<String>get("lastName"), "%2%")
                    ),
                    cb.equal(root.get("name"), "Lermontov")
            ));
            //этап выполнения запроса
            Query query = session.createQuery(cd);
            query.executeUpdate();

            return null;
        });
    }

}
