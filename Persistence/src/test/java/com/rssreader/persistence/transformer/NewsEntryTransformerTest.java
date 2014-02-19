package com.rssreader.persistence.transformer;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.model.impl.NewsEntry;
import com.rssreader.persistence.dao.impl.NewsChannelDAO;
import com.rssreader.persistence.dao.impl.NewsEntryDAO;
import com.rssreader.persistence.transformer.impl.NewsEntryTransformer;

public class NewsEntryTransformerTest {

    private static final String NAME = "habr entry";
    private static final int ID = 1;
    private static final Calendar DATE = Calendar.getInstance();
    private static final Integer RATING = 2;
    private static final String TEXT = "News Text";
    private static final int CHANNEL_ID = 3;
    private static final String CHANNEL_LINK = "http:/nowhere";
    private static final String CHANNEL_NAME = "channel name";
    private static final String LINK = "http://entryLink";
    private NewsEntryTransformer underTest;

    @Before
    public void setUp() {
        underTest = new NewsEntryTransformer();
    }

    @Test
    public void shouldTransformToDAO() {
        NewsChannel fromChannel = new NewsChannel();
        fromChannel.setId(CHANNEL_ID);
        fromChannel.setLink(CHANNEL_LINK);
        fromChannel.setName(CHANNEL_NAME);

        NewsEntry from = new NewsEntry();
        from.setId(ID);
        from.setName(NAME);
        from.setDatePublished(DATE);
        from.setRating(RATING);
        from.setText(TEXT);
        from.setChannel(fromChannel);
        from.setLink(LINK);

        NewsEntryDAO to = underTest.transformToDAO(from);

        assertEquals(ID, to.getId());
        assertEquals(NAME, to.getName());
        assertEquals(DATE, to.getDatePublished());
        assertEquals(RATING, to.getRating());
        assertEquals(TEXT, to.getText());
        assertEquals(LINK, to.getLink());
        assertEquals(CHANNEL_ID, to.getChannel().getId());
        assertEquals(CHANNEL_LINK, to.getChannel().getLink());
        assertEquals(CHANNEL_NAME, to.getChannel().getName());
    }

    @Test
    public void shouldTransformToModel() {
        NewsChannelDAO fromChannel = new NewsChannelDAO();
        fromChannel.setId(CHANNEL_ID);
        fromChannel.setLink(CHANNEL_LINK);
        fromChannel.setName(CHANNEL_NAME);

        NewsEntryDAO from = new NewsEntryDAO();
        from.setId(ID);
        from.setName(NAME);
        from.setDatePublished(DATE);
        from.setRating(RATING);
        from.setText(TEXT);
        from.setChannel(fromChannel);
        from.setLink(LINK);

        NewsEntry to = underTest.transformToModel(from);

        assertEquals(ID, to.getId());
        assertEquals(NAME, to.getName());
        assertEquals(DATE, to.getDatePublished());
        assertEquals(RATING, to.getRating());
        assertEquals(TEXT, to.getText());
        assertEquals(CHANNEL_ID, to.getChannel().getId());
        assertEquals(CHANNEL_LINK, to.getChannel().getLink());
        assertEquals(CHANNEL_NAME, to.getChannel().getName());
        assertEquals(LINK, to.getLink());
    }
}
