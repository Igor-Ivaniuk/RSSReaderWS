package com.rssreader.persistence.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.model.impl.NewsEntry;
import com.rssreader.persistence.dao.impl.NewsChannelDAO;
import com.rssreader.persistence.dao.impl.NewsEntryDAO;
import com.rssreader.persistence.processor.helper.DBProcessorHelper;
import com.rssreader.persistence.transformer.impl.NewsEntryTransformer;

public class NewsEntryDBProcessor {
    private static final int TOP_ENTRIES_COUNT = 100;
    private static final String QUERY_TOP_ENTRIES = "FROM NewsEntryDAO neD ORDER BY neD.rating desc, neD.datePublished desc";
    private static final String QUERY_TOP_ENTRIES_BY_CHANNELS_FROM = "FROM NewsEntryDAO neD WHERE neD.channel IN ";
    private static final String QUERY_TOP_ENTRIES_BY_CHANNELS_ORDER = "ORDER BY neD.rating desc, neD.datePublished desc";
    private static final String QUERY_FIND_ENTRY_BY_ID = "FROM NewsEntryDAO neD WHERE neD.id = ";
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private NewsEntryTransformer newsEntryTransformer = new NewsEntryTransformer();

    public void createNewsEntry(NewsEntry newsEntry) {
        NewsEntryDAO newsEntryDAO = newsEntryTransformer
                .transformToDAO(newsEntry);

        entityManager = DBProcessorHelper.initEntityManager(
                entityManagerFactory, entityManager,
                DBProcessorHelper.PERSISTENCE_UNIT_NAME);

        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(newsEntryDAO);

        entityTransaction.commit();

        DBProcessorHelper.closeEntityManager(entityManager);
    }

    public void createMultipleNewsEntriesSameChannel(
            List<NewsEntry> newsEntryList) {
        List<NewsEntryDAO> newsEntryDAOList = new ArrayList<NewsEntryDAO>();
        for (NewsEntry newsEntry : newsEntryList) {
            NewsEntryDAO newsEntryDAO = newsEntryTransformer
                    .transformToDAO(newsEntry);
            newsEntryDAOList.add(newsEntryDAO);
        }

        entityManager = DBProcessorHelper.initEntityManager(
                entityManagerFactory, entityManager,
                DBProcessorHelper.PERSISTENCE_UNIT_NAME);

        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        NewsChannelDAO newsChannelDAO = newsEntryDAOList.get(0).getChannel();
        entityManager.persist(newsChannelDAO);

        for (NewsEntryDAO newsEntryDAO : newsEntryDAOList) {
            newsEntryDAO.setChannel(newsChannelDAO);
            entityManager.persist(newsEntryDAO);
        }

        entityTransaction.commit();
        DBProcessorHelper.closeEntityManager(entityManager);
    }

    public List<NewsEntry> getTopNewsEntries() {
        return queryEntries(QUERY_TOP_ENTRIES);
    }

    public List<NewsEntry> getTopNewsEntries(List<NewsChannel> newsChannelList) {
        return queryEntries(buildQueryByChannels(newsChannelList));
    }

    @SuppressWarnings("unchecked")
    public NewsEntry findNewsEntryById(Integer newsId) {
        entityManager = DBProcessorHelper.initEntityManager(
                entityManagerFactory, entityManager,
                DBProcessorHelper.PERSISTENCE_UNIT_NAME);

        Query query = entityManager.createQuery(QUERY_FIND_ENTRY_BY_ID
                + newsId.toString());
        Collection<NewsEntryDAO> resultSet = (Collection<NewsEntryDAO>) query
                .getResultList();

        DBProcessorHelper.closeEntityManager(entityManager);

        if (resultSet.size() > 0) {
            NewsEntry newsEntry = newsEntryTransformer
                    .transformToModel(resultSet.iterator().next());
            return newsEntry;
        }

        return null;
    }

    public void updateNewsEntry(NewsEntry newsEntry) {
        NewsEntryDAO newsEntryDAO = newsEntryTransformer
                .transformToDAO(newsEntry);
        
        entityManager = DBProcessorHelper.initEntityManager(
                entityManagerFactory, entityManager,
                DBProcessorHelper.PERSISTENCE_UNIT_NAME);

        entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.merge(newsEntryDAO);

        entityTransaction.commit();

        DBProcessorHelper.closeEntityManager(entityManager);

    }

    public void setEntityManagerFactory(
            EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setEntityTransaction(EntityTransaction entityTransaction) {
        this.entityTransaction = entityTransaction;
    }

    private String buildQueryByChannels(List<NewsChannel> newsChannelList) {
        StringBuffer sb = new StringBuffer();
        sb.append(QUERY_TOP_ENTRIES_BY_CHANNELS_FROM);
        sb.append("(");
        for (int i = 0; i < newsChannelList.size() - 1; i++) {
            sb.append(newsChannelList.get(i).getId());
            sb.append(", ");
        }
        sb.append(newsChannelList.get(newsChannelList.size() - 1).getId());
        sb.append(") ");
        sb.append(QUERY_TOP_ENTRIES_BY_CHANNELS_ORDER);
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private List<NewsEntry> queryEntries(String queryString) {
        entityManager = DBProcessorHelper.initEntityManager(
                entityManagerFactory, entityManager,
                DBProcessorHelper.PERSISTENCE_UNIT_NAME);

        Query query = entityManager.createQuery(queryString);
        query.setMaxResults(TOP_ENTRIES_COUNT);

        Collection<NewsEntryDAO> resultSet = (Collection<NewsEntryDAO>) query
                .getResultList();

        DBProcessorHelper.closeEntityManager(entityManager);

        List<NewsEntry> newsEntryArray = new ArrayList<NewsEntry>();
        for (NewsEntryDAO newsEntryDAO : resultSet) {
            NewsEntry newsEntry = newsEntryTransformer
                    .transformToModel(newsEntryDAO);
            newsEntryArray.add(newsEntry);
        }

        return newsEntryArray;
    }

}
