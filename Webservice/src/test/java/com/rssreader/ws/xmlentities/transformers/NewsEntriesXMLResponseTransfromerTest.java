package com.rssreader.ws.xmlentities.transformers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.model.impl.NewsEntry;
import com.rssreader.ws.xmlentities.NewsEntriesXMLResponse;
import com.rssreader.ws.xmlentities.entries.NewsEntriesXMLResponseEntry;

public class NewsEntriesXMLResponseTransfromerTest {

    private static final String NC_NAME_1 = "name1";
    private static final String NC_TITLE_1 = "title1";
    private static final String NC_NAME_2 = "name2";
    private static final String NC_TITLE_2 = "title2";
    private static final int NE_ID_1 = 1;
    private static final String NE_LINK_1 = "link1";
    private static final String NE_NAME_1 = "ne_name1";
    private static final Integer NE_RATING_1 = 3;
    private static final int NE_ID_2 = 2;
    private static final String NE_LINK_2 = "link2";
    private static final String NE_NAME_2 = "ne_name2";
    private static final Integer NE_RATING_2 = 4;

    private NewsEntriesXMLResponseTransfromer underTest;

    @Before
    public void setUp() {
        underTest = new NewsEntriesXMLResponseTransfromer();
    }

    @Test
    public void shouldTransfrormToNewsEntriesXMLResponse() {
        NewsChannel newsChannel1 = new NewsChannel();
        newsChannel1.setName(NC_NAME_1);
        newsChannel1.setTitle(NC_TITLE_1);

        NewsChannel newsChannel2 = new NewsChannel();
        newsChannel2.setName(NC_NAME_2);
        newsChannel2.setTitle(NC_TITLE_2);

        NewsEntry newsEntry1 = new NewsEntry();
        newsEntry1.setChannel(newsChannel1);
        newsEntry1.setId(NE_ID_1);
        newsEntry1.setLink(NE_LINK_1);
        newsEntry1.setName(NE_NAME_1);
        newsEntry1.setRating(NE_RATING_1);

        NewsEntry newsEntry2 = new NewsEntry();
        newsEntry2.setChannel(newsChannel2);
        newsEntry2.setId(NE_ID_2);
        newsEntry2.setLink(NE_LINK_2);
        newsEntry2.setName(NE_NAME_2);
        newsEntry2.setRating(NE_RATING_2);

        List<NewsEntry> neList = new ArrayList<NewsEntry>();

        neList.add(newsEntry1);
        neList.add(newsEntry2);

        NewsEntriesXMLResponse result = underTest
                .transfrormToNewsEntriesXMLResponse(neList);

        assertNotNull(result);
        assertNotNull(result.getNewsEntriesList());
        List<NewsEntriesXMLResponseEntry> responseList = result
                .getNewsEntriesList();
        assertEquals(neList.size(), responseList.size());

        assertEquals(newsChannel1.getName(), responseList.get(0).getChannel());
        assertEquals(newsEntry1.getId(),
                Integer.parseInt(responseList.get(0).getId()));
        assertEquals(newsEntry1.getLink(), responseList.get(0).getLink());
        assertEquals(newsEntry1.getName(), responseList.get(0).getName());
        assertEquals(newsEntry1.getRating(), responseList.get(0).getRank());

        assertEquals(newsChannel2.getName(), responseList.get(1).getChannel());
        assertEquals(newsEntry2.getId(),
                Integer.parseInt(responseList.get(1).getId()));
        assertEquals(newsEntry2.getLink(), responseList.get(1).getLink());
        assertEquals(newsEntry2.getName(), responseList.get(1).getName());
        assertEquals(newsEntry2.getRating(), responseList.get(1).getRank());

    }

}
