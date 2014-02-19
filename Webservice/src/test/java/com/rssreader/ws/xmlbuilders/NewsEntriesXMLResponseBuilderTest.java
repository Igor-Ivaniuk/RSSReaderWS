package com.rssreader.ws.xmlbuilders;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.ws.xmlbuilders.NewsChannelsXMLResponseBuilder;

public class NewsEntriesXMLResponseBuilderTest {

    private static final String CHANNEL1_TITLE = "nc1title";
    private static final String CHANNEL1_NAME = "nc1name";
    private static final String CHANNEL1_LINK = "http://link1";
    private static final String CHANNEL2_TITLE = "nc2title";
    private static final String CHANNEL2_NAME = "nc2name";
    private static final String CHANNEL2_LINK = "http://link2";
    private static final String RESULT = "<channels>" + "\n"
            + "  <channel id=\"" + CHANNEL1_NAME + "\">" + CHANNEL1_TITLE
            + "</channel>" + "\n" + "  <channel id=\"" + CHANNEL2_NAME + "\">"
            + CHANNEL2_TITLE + "</channel>" + "\n" + "</channels>";
    private NewsChannelsXMLResponseBuilder underTest;

    @Test
    public void shouldTransformNewsChannelList() throws Exception {
        underTest = new NewsChannelsXMLResponseBuilder();
        NewsChannel nc1 = new NewsChannel();
        nc1.setId(1);
        nc1.setLink(CHANNEL1_LINK);
        nc1.setName(CHANNEL1_NAME);
        nc1.setTitle(CHANNEL1_TITLE);
        NewsChannel nc2 = new NewsChannel();
        nc2.setId(1);
        nc2.setLink(CHANNEL2_LINK);
        nc2.setName(CHANNEL2_NAME);
        nc2.setTitle(CHANNEL2_TITLE);
        List<NewsChannel> ncList = new ArrayList<NewsChannel>();
        ncList.add(nc1);
        ncList.add(nc2);
        String result = null;

        result = underTest.buildResponse(ncList);

        assertEquals(RESULT, result);

    }
}
