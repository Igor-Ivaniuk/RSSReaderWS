package com.rssreader.business;

import java.util.List;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.persistence.processor.NewsChannelDBProcessor;

public class NewsChannelsBL {

    private NewsChannelDBProcessor dbHelper;

    public List<NewsChannel> getNewsChannels() {
        if (dbHelper == null) {
            dbHelper = new NewsChannelDBProcessor();
        }
        return dbHelper.getNewsChannels();
    }

    public NewsChannelDBProcessor getDbHelper() {
        return dbHelper;
    }

    public void setDbHelper(NewsChannelDBProcessor dbHelper) {
        this.dbHelper = dbHelper;
    }

}
