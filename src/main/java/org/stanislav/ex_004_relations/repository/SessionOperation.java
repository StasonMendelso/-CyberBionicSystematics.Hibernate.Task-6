package org.stanislav.ex_004_relations.repository;

import org.hibernate.Session;

/**
 * @author Stanislav Hlova
 */
public interface SessionOperation<T> {
    T execute(Session session);
}