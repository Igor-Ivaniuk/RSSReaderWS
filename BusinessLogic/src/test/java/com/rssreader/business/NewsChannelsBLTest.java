package com.rssreader.business;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.persistence.processor.NewsChannelDBProcessor;

public class NewsChannelsBLTest {

    @Mock
    private NewsChannelDBProcessor mockDbHelper;

    private NewsChannelsBL underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(mockDbHelper.getNewsChannels()).thenReturn(
                new ArrayList<NewsChannel>());

        underTest = new NewsChannelsBL();
        underTest.setDbHelper(mockDbHelper);
    }

    @Test
    public void shouldCallDBHelper() {
        underTest.getNewsChannels();
        verify(mockDbHelper).getNewsChannels();
    }

}
