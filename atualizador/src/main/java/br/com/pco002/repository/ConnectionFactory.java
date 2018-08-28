package br.com.pco002.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("rss-persistence");

    public static EntityManager getEntityManager() {
            return factory.createEntityManager();
    }

}
