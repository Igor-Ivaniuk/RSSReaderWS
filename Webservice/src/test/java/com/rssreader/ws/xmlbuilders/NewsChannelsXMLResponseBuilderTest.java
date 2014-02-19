package com.rssreader.ws.xmlbuilders;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.model.impl.NewsEntry;

public class NewsChannelsXMLResponseBuilderTest {

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

    private static final String RESULT = "<news>\n  <item id=\"1\" channel=\"name1\" link=\"link1\" rank=\"3\">ne_name1</item>\n  <item id=\"2\" channel=\"name2\" link=\"link2\" rank=\"4\">ne_name2</item>\n</news>";
    private NewsEntriesXMLResponseBuilder underTest;

    @Test
    public void shouldTransformNewsChannelList() throws Exception {
        underTest = new NewsEntriesXMLResponseBuilder();

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

        String result = null;

        result = underTest.buildResponse(neList);
        assertEquals(RESULT, result);

    }
}
