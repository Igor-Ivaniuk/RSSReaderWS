package com.rssreader.ws.xmlentities.transformers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.ws.xmlentities.NewsChannelsXMLResponse;
import com.rssreader.ws.xmlentities.entries.NewsChannelsXMLResponseEntry;

public class NewsChannelsXMLResponseTransfromerTest {

    private static final String NC_NAME_1 = "name1";
    private static final String NC_TITLE_1 = "title1";
    private static final String NC_NAME_2 = "name2";
    private static final String NC_TITLE_2 = "title2";
    
    private NewsChannelsXMLResponseTransfromer underTest;

    @Before
    public void setUp() {
        underTest = new NewsChannelsXMLResponseTransfromer();
    }

    @Test
    public void shouldTransfrormToNewsChannelsXMLResponse() {
        NewsChannel newsChannel1 = new NewsChannel();
        newsChannel1.setName(NC_NAME_1);
        newsChannel1.setTitle(NC_TITLE_1);
        NewsChannel newsChannel2 = new NewsChannel();
        newsChannel2.setName(NC_NAME_2);
        newsChannel2.setTitle(NC_TITLE_2);
        List<NewsChannel> ncList = new ArrayList<NewsChannel>();
        ncList.add(newsChannel1);
        ncList.add(newsChannel2);
        
        NewsChannelsXMLResponse result = underTest.transfrormToNewsChannelsXMLResponse(ncList);
        
        assertNotNull(result);
        assertNotNull(result.getNewsChannelList());
        List<NewsChannelsXMLResponseEntry> responseList = result.getNewsChannelList();
        assertEquals(ncList.size(), responseList.size());
        assertEquals(newsChannel1.getName(), responseList.get(0).getId());
        assertEquals(newsChannel1.getTitle(), responseList.get(0).getTitle());
        assertEquals(newsChannel2.getName(), responseList.get(1).getId());
        assertEquals(newsChannel2.getTitle(), responseList.get(1).getTitle());

    }

}
