package com.rssreader.persistence.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.persistence.dao.impl.NewsChannelDAO;
import com.rssreader.persistence.processor.helper.DBProcessorHelper;
import com.rssreader.persistence.transformer.impl.NewsChannelTransformer;

public class NewsChannelDBProcessor {
    private static final String QUERY_ALL_CHANNELS = "FROM NewsChannelDAO";
    private static final String QUERY_CHANNEL_BY_NAME = "FROM NewsChannelDAO ncd WHERE ncd.name = ";
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private NewsChannelTransformer newsChannelTransformer = new NewsChannelTransformer();

    public List<NewsChannel> getNewsChannels() {
        return queryChannels(QUERY_ALL_CHANNELS);
    }

    public List<NewsChannel> findChannelByName(String channel) {
        return queryChannels(buildQueryChannelByName(channel));

    }

    public void setEntityManagerFactory(
            EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void setEntityTransaction(EntityTransaction entityTransaction) {
    }

    private String buildQueryChannelByName(String channel) {
        return QUERY_CHANNEL_BY_NAME + "'" + channel + "'";
    }

    @SuppressWarnings("unchecked")
    private List<NewsChannel> queryChannels(String queryString) {
        entityManager = DBProcessorHelper.initEntityManager(
                entityManagerFactory, entityManager,
                DBProcessorHelper.PERSISTENCE_UNIT_NAME);

        Query query = entityManager.createQuery(queryString);

        Collection<NewsChannelDAO> resultSet = (Collection<NewsChannelDAO>) query
                .getResultList();

        DBProcessorHelper.closeEntityManager(entityManager);

        List<NewsChannel> newsChannelArray = new ArrayList<NewsChannel>();
        for (NewsChannelDAO newsChannelDAO : resultSet) {
            NewsChannel newsChannel = newsChannelTransformer
                    .transformToModel(newsChannelDAO);
            newsChannelArray.add(newsChannel);
        }

        return newsChannelArray;
    }

}
