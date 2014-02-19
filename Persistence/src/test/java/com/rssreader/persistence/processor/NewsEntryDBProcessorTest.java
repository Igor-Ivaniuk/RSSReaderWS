package com.rssreader.persistence.processor;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.rssreader.model.impl.NewsEntry;
import com.rssreader.persistence.dao.impl.NewsChannelDAO;
import com.rssreader.persistence.dao.impl.NewsEntryDAO;
import com.rssreader.persistence.processor.NewsEntryDBProcessor;

public class NewsEntryDBProcessorTest {

    private static final Integer ENTRY_ID_NOT_FOUND = 1;
    private static final Integer ENTRY_ID_CORRECT = 2;
    private static final int ENTITY_COUNTER = 5;
    private static final String ENTRY_NAME = "Entry name";
    private static final Calendar ENTRY_DATE = Calendar.getInstance();
    private static final int ENTRY_ID = 3;
    private static final String ENTRY_LINK = "elink";
    private static final Integer ENTRY_RATING = 4;
    private static final String ENTRY_TEXT = "entrytext";
    private static final int CHANNEL_ID = 1;
    private static final String CHANNEL_LINK = "http://";
    private static final String CHANNEL_NAME = "vhannel name";
    private static final String CHANNEL_TITLE = "channel title";

    private NewsEntryDBProcessor underTest;

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
        underTest = new NewsEntryDBProcessor();
        underTest.setEntityManager(mockEntityManager);
        underTest.setEntityManagerFactory(mockEntityManagerFactory);
        when(mockEntityManagerFactory.createEntityManager()).thenReturn(
                mockEntityManager);
        when(mockEntityManager.getTransaction()).thenReturn(
                mockEntityTransaction);
        doNothing().when(mockEntityTransaction).begin();
        doNothing().when(mockEntityTransaction).commit();
        doNothing().when(mockEntityManager).persist(any());
        doNothing().when(mockEntityManager).close();
        doNothing().when(mockEntityManagerFactory).close();
    }

    @Test
    public void shouldPersistNewsEntry() {
        NewsEntry newsEntry = new NewsEntry();
        newsEntry.setName(ENTRY_NAME);

        underTest.createNewsEntry(newsEntry);

        verify(mockEntityManager).persist(any());
    }

    @Test
    public void shouldPersistMultipleNewsEntriesSameChannel() {
        List<NewsEntry> newsEntriesList = new ArrayList<NewsEntry>();
        for (int i = 0; i < ENTITY_COUNTER; i++) {
            NewsEntry newsEntry = new NewsEntry();
            newsEntry.setName(ENTRY_NAME);
            newsEntriesList.add(newsEntry);
        }

        underTest.createMultipleNewsEntriesSameChannel(newsEntriesList);

        verify(mockEntityManager, times(ENTITY_COUNTER + 1)).persist(any());
    }

    @Test
    public void shouldReturnTopEntriesList() {

        givenWeHaveAMockQuery();

        List<NewsEntry> result = underTest.getTopNewsEntries();

        verify(mockQuery).getResultList();
        assertTrue(result != null);
        assertTrue(result.size() > 0);
        assertEquals(CHANNEL_ID, result.get(0).getChannel().getId());
        assertEquals(CHANNEL_LINK, result.get(0).getChannel().getLink());
        assertEquals(CHANNEL_NAME, result.get(0).getChannel().getName());
        assertEquals(CHANNEL_TITLE, result.get(0).getChannel().getTitle());
        assertEquals(ENTRY_ID, result.get(0).getId());
        assertEquals(ENTRY_DATE, result.get(0).getDatePublished());
        assertEquals(ENTRY_LINK, result.get(0).getLink());
        assertEquals(ENTRY_NAME, result.get(0).getName());
        assertEquals(ENTRY_RATING, result.get(0).getRating());
        assertEquals(ENTRY_TEXT, result.get(0).getText());

    }

    @Test
    public void shouldReturnTopEntriesListByChanel() {

        givenWeHaveAMockQuery();

        NewsChannel newsChannel = new NewsChannel();
        newsChannel.setId(CHANNEL_ID);
        newsChannel.setLink(CHANNEL_LINK);
        newsChannel.setName(CHANNEL_NAME);
        newsChannel.setTitle(CHANNEL_TITLE);
        List<NewsChannel> ncList = new ArrayList<NewsChannel>();
        ncList.add(newsChannel);

        List<NewsEntry> result = underTest.getTopNewsEntries(ncList);

        verify(mockQuery).getResultList();
        assertTrue(result != null);
        assertTrue(result.size() > 0);
        assertEquals(CHANNEL_ID, result.get(0).getChannel().getId());
        assertEquals(CHANNEL_LINK, result.get(0).getChannel().getLink());
        assertEquals(CHANNEL_NAME, result.get(0).getChannel().getName());
        assertEquals(CHANNEL_TITLE, result.get(0).getChannel().getTitle());
        assertEquals(ENTRY_ID, result.get(0).getId());
        assertEquals(ENTRY_DATE, result.get(0).getDatePublished());
        assertEquals(ENTRY_LINK, result.get(0).getLink());
        assertEquals(ENTRY_NAME, result.get(0).getName());
        assertEquals(ENTRY_RATING, result.get(0).getRating());
        assertEquals(ENTRY_TEXT, result.get(0).getText());

    }

    @Test
    public void shouldReturnNullIfNoEntriesFoundOnGivenId() {

        givenWeHaveAWrongIdMockQuery();

        NewsEntry result = underTest.findNewsEntryById(ENTRY_ID_NOT_FOUND);

        verify(mockQuery).getResultList();
        assertNull(result);
    }

    @Test
    public void shouldReturnNewsEntryOnGivenId() {

        givenWeHaveAMockQuery();

        NewsEntry result = underTest.findNewsEntryById(ENTRY_ID_CORRECT);

        verify(mockQuery).getResultList();
        assertNotNull(result);
    }

    private void givenWeHaveAWrongIdMockQuery() {
        when(mockEntityManager.createQuery((String) any())).thenReturn(
                mockQuery);
        List<NewsEntryDAO> resultSet = new ArrayList<NewsEntryDAO>();
        when(mockQuery.getResultList()).thenReturn(
                (List<NewsEntryDAO>) resultSet);

    }

    private void givenWeHaveAMockQuery() {

        when(mockEntityManager.createQuery((String) any())).thenReturn(
                mockQuery);

        List<NewsEntryDAO> resultSet = new ArrayList<NewsEntryDAO>();
        NewsChannelDAO newsChannelDAO = new NewsChannelDAO();
        newsChannelDAO.setId(CHANNEL_ID);
        newsChannelDAO.setLink(CHANNEL_LINK);
        newsChannelDAO.setName(CHANNEL_NAME);
        newsChannelDAO.setTitle(CHANNEL_TITLE);

        NewsEntryDAO newsEntryDAO = new NewsEntryDAO();
        newsEntryDAO.setChannel(newsChannelDAO);
        newsEntryDAO.setDatePublished(ENTRY_DATE);
        newsEntryDAO.setId(ENTRY_ID);
        newsEntryDAO.setLink(ENTRY_LINK);
        newsEntryDAO.setName(ENTRY_NAME);
        newsEntryDAO.setRating(ENTRY_RATING);
        newsEntryDAO.setText(ENTRY_TEXT);

        resultSet.add(newsEntryDAO);
        when(mockQuery.getResultList()).thenReturn(
                (List<NewsEntryDAO>) resultSet);
    }

}
