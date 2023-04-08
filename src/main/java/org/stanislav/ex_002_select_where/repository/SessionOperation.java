package org.stanislav.ex_002_select_where.repository;

import org.hibernate.Session;

/**
 * @author Stanislav Hlova
 */
public interface SessionOperation<T> {
    T execute(Session session);
}