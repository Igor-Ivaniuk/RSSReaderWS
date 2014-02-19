package com.rssreader.persistence.processor.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBProcessorHelper {

    public static final String PERSISTENCE_UNIT_NAME = "RSSReaderPU";

    public static EntityManager initEntityManager(
            EntityManagerFactory entityManagerFactory,
            EntityManager entityManager, String persistenceUnitName) {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence
                    .createEntityManagerFactory(persistenceUnitName);
        }
        entityManager = entityManagerFactory.createEntityManager();
        entityManagerFactory.close();
        return entityManager;
    }

    public static void closeEntityManager(EntityManager entityManager) {
        entityManager.close();

    }

}
