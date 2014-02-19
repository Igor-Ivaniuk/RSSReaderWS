package com.rssreader.persistence.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.persistence.dao.impl.NewsChannelDAO;
import com.rssreader.persistence.processor.NewsChannelDBProcessor;

public class NewsChannelDBProcessorTest {
    private static final int CHANNEL_ID = 1;
    private static final String CHANNEL_LINK = "http://";
    private static final String CHANNEL_NAME = "vhannel name";
    private static final String CHANNEL_TITLE = "channel title";

    private NewsChannelDBProcessor underTest;

    @Mock
    private EntityManager mockEntityManager;
    @Mock
    private EntityManagerFactory mockEntityManagerFactory;
    @Mock
    private EntityTransaction mockEntityTransaction;
    @Mock
    private Query mockQuery;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new NewsChannelDBProcessor();
        underTest.setEntityManager(mockEntityManager);
        underTest.setEntityManagerFactory(mockEntityManagerFactory);
        when(mockEntityManagerFactory.createEntityManager()).thenReturn(
                mockEntityManager);
        when(mockEntityManager.getTransaction()).thenReturn(
                mockEntityTransaction);
        when(mockEntityManager.createQuery((String) any())).thenReturn(
                mockQuery);

        List<NewsChannelDAO> resultSet = new ArrayList<NewsChannelDAO>();
        NewsChannelDAO newsChannelDAO = new NewsChannelDAO();
        newsChannelDAO.setId(CHANNEL_ID);
        newsChannelDAO.setLink(CHANNEL_LINK);
        newsChannelDAO.setName(CHANNEL_NAME);
        newsChannelDAO.setTitle(CHANNEL_TITLE);
        resultSet.add(newsChannelDAO);
        when(mockQuery.getResultList()).thenReturn(
                (List<NewsChannelDAO>) resultSet);

        doNothing().when(mockEntityTransaction).begin();
        doNothing().when(mockEntityTransaction).commit();
        doNothing().when(mockEntityManager).persist(any());
        doNothing().when(mockEntityManager).close();
        doNothing().when(mockEntityManagerFactory).close();
    }

    @Test
    public void shouldGetNewsChannelsList() {
        List<NewsChannel> result = underTest.getNewsChannels();

        verify(mockQuery).getResultList();
        assertTrue(result != null);
        assertTrue(result.size() > 0);
        assertEquals(CHANNEL_ID, result.get(0).getId());
        assertEquals(CHANNEL_LINK, result.get(0).getLink());
        assertEquals(CHANNEL_NAME, result.get(0).getName());
        assertEquals(CHANNEL_TITLE, result.get(0).getTitle());
    }

}
