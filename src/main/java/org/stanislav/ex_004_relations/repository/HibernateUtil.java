package org.stanislav.ex_004_relations.repository;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Asus on 01.11.2017.
 */
public class HibernateUtil {

    private static SessionFactory factory;
    private static final Logger LOG = Logger.getLogger(HibernateUtil.class.getName());

    static {
        try {
            factory = new Configuration()
                    .configure("ex_004_config.xml")
                    .buildSessionFactory();
        } catch (HibernateException e) {
            LOG.error(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

}
