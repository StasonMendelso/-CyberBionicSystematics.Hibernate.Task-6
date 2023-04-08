package org.stanislav.ex_004_relations.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * @author Stanislav Hlova
 */
public abstract class Repository {
    private final SessionFactory sessionFactory;

    protected Repository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected <T> T doInTransaction(SessionOperation<T> sessionOperation) {
        T result;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            result = sessionOperation.execute(session);
            session.getTransaction().commit();
        }
        return result;
    }
}
