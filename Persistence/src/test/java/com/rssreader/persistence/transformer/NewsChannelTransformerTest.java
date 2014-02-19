package com.rssreader.persistence.transformer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.persistence.dao.impl.NewsChannelDAO;
import com.rssreader.persistence.transformer.impl.NewsChannelTransformer;

public class NewsChannelTransformerTest {

    private static final String NAME = "habr";
    private static final String LINK = "http://nowhere";
    private static final String TITLE = "Habrahabr.ru";
    private static final int ID = 1;
    private NewsChannelTransformer underTest;

    @Before
    public void setUp() {
        underTest = new NewsChannelTransformer();
    }

    @Test
    public void shouldTransformToDAO() {
        NewsChannel from = new NewsChannel();
        from.setId(ID);
        from.setLink(LINK);
        from.setName(NAME);
        from.setTitle(TITLE);

        NewsChannelDAO to = underTest.transformToDAO(from);

        assertEquals(ID, to.getId());
        assertEquals(LINK, to.getLink());
        assertEquals(NAME, to.getName());
        assertEquals(TITLE, to.getTitle());
    }

    @Test
    public void shouldTransformToModel() {
        NewsChannelDAO from = new NewsChannelDAO();
        from.setId(ID);
        from.setLink(LINK);
        from.setName(NAME);
        from.setTitle(TITLE);

        NewsChannel to = underTest.transformToModel((from));

        assertEquals(ID, to.getId());
        assertEquals(LINK, to.getLink());
        assertEquals(NAME, to.getName());
        assertEquals(TITLE, to.getTitle());
    }
}
