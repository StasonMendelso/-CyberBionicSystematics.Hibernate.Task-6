package org.stanislav.ex_004_relations.repository;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.persistence.metamodel.SingularAttribute;
import org.hibernate.metamodel.model.domain.internal.SingularAttributeImpl;
import org.stanislav.ex_004_relations.entity.Author;
import org.stanislav.ex_004_relations.entity.Book;

import java.util.List;

public class BookRepository extends Repository {


    public BookRepository() {
        super(HibernateUtil.getSessionFactory());
    }

    public List<Book> getBookList() {
        return doInTransaction(session -> {
            CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
            CriteriaQuery<Book> cq = cb.createQuery(Book.class);
            Root<Book> root = cq.from(Book.class);// первостепенный, корневой entity (в sql запросе - from)
            cq.select(root);
            //этап выполнения запроса
            Query query = session.createQuery(cq);
            return (List<Book>) query.getResultList();
        });
    }

    public boolean deleteById(int id) {
        return doInTransaction(session -> {
            Book book = session.get(Book.class, id);
            if (book == null) {
                return false;
            }
            session.remove(book);
            return session.get(Book.class, id) == null;
        });
    }

    public boolean deleteBooksByAuthorName(Author author) {
        return doInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<Book> criteriaDelete = criteriaBuilder.createCriteriaDelete(Book.class);
            Root<Book> root = criteriaDelete.from(Book.class);
            criteriaDelete.where(criteriaBuilder.equal(root.get("author"),author));

            return session.createQuery(criteriaDelete).executeUpdate() > 0;
        });
    }

}
