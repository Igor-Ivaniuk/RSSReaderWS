package com.rssreader.business;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.model.impl.NewsEntry;
import com.rssreader.persistence.processor.NewsChannelDBProcessor;
import com.rssreader.persistence.processor.NewsEntryDBProcessor;

public class NewsEntriesBLTest {

    private static final String NEWS_ID_INVALID = "Wrong_news_id";
    private static final Integer NEWS_ID_NOT_FOUND = 1;
    private static final Integer NEWS_ID_CORRECT = 2;
    private static final String WRONG_CHANNEL = "wrong_channel";
    private static final String RIGHT_CHANNEL = "right_channel";
    private static final Object RATIO_RESPONSE_CORRECT = "News entry with id \"2\" now has rating 2";

    @Mock
    private NewsEntryDBProcessor mockEntryDBProcessor;
    @Mock
    private NewsChannelDBProcessor mockChannelDBProcessor;

    private List<NewsChannel> ncList;
    private NewsEntry ne;

    private NewsEntriesBL underTest;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockEntryDBProcessor.getTopNewsEntries()).thenReturn(
                new ArrayList<NewsEntry>());
        when(mockEntryDBProcessor.getTopNewsEntries((List<NewsChannel>) any()))
                .thenReturn(new ArrayList<NewsEntry>());
        when(mockEntryDBProcessor.findNewsEntryById(NEWS_ID_NOT_FOUND))
                .thenReturn(null);
        ne = new NewsEntry();
        ne.setRating(1);
        when(mockEntryDBProcessor.findNewsEntryById(NEWS_ID_CORRECT))
                .thenReturn(ne);
        doNothing().when(mockEntryDBProcessor).updateNewsEntry(
                (NewsEntry) any());

        when(mockChannelDBProcessor.findChannelByName(WRONG_CHANNEL))
                .thenReturn(new ArrayList<NewsChannel>());
        NewsChannel nc = new NewsChannel();
        nc.setName(RIGHT_CHANNEL);
        ncList = new ArrayList<NewsChannel>();
        ncList.add(nc);
        when(mockChannelDBProcessor.findChannelByName(RIGHT_CHANNEL))
                .thenReturn(ncList);

        underTest = new NewsEntriesBL();
        underTest.setEntryDBHelper(mockEntryDBProcessor);
        underTest.setChannelDBHelper(mockChannelDBProcessor);
    }

    @Test
    public void shouldCallDBHelperTopNewsEntries() {
        underTest.getTopNewsEntries(null);
        verify(mockEntryDBProcessor).getTopNewsEntries();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotCallDBHelperTopNewsEntriesByChannelIfChannelListEmpty() {
        underTest.getTopNewsEntries(WRONG_CHANNEL);
        verify(mockChannelDBProcessor).findChannelByName(WRONG_CHANNEL);
        verify(mockEntryDBProcessor, times(0)).getTopNewsEntries(
                (List<NewsChannel>) any());
    }

    @Test
    public void shouldCallDBHelperTopNewsEntriesByChannel() {
        underTest.getTopNewsEntries(RIGHT_CHANNEL);
        verify(mockChannelDBProcessor).findChannelByName(RIGHT_CHANNEL);
        verify(mockEntryDBProcessor).getTopNewsEntries((ncList));
    }

    @Test
    public void shouldReturnInvalidIdResponse() {
        String response = underTest.increaseRating(NEWS_ID_INVALID);
        assertEquals(NewsEntriesBL.RESPONSE_INVALID_NEWS_ID, response);
    }

    @Test
    public void shouldReturnNotFoundResponse() {
        String response = underTest
                .increaseRating(NEWS_ID_NOT_FOUND.toString());
        assertEquals(NewsEntriesBL.RESPONSE_NEWS_NOT_FOUND, response);
        verify(mockEntryDBProcessor).findNewsEntryById(NEWS_ID_NOT_FOUND);
    }

    @Test
    public void shouldReturnCorrectResponse() {
        String response = underTest.increaseRating(NEWS_ID_CORRECT.toString());
        assertEquals(RATIO_RESPONSE_CORRECT, response);
        verify(mockEntryDBProcessor).findNewsEntryById(NEWS_ID_CORRECT);
        verify(mockEntryDBProcessor).updateNewsEntry(ne);
    }
}
