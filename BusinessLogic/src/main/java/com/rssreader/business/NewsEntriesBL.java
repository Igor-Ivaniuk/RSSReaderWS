package com.rssreader.business;

import java.util.ArrayList;
import java.util.List;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.model.impl.NewsEntry;
import com.rssreader.persistence.processor.NewsChannelDBProcessor;
import com.rssreader.persistence.processor.NewsEntryDBProcessor;

public class NewsEntriesBL {

    public static final String RESPONSE_INVALID_NEWS_ID = "Invalid news ID";
    public static final String RESPONSE_NEWS_NOT_FOUND = "Not found news with given ID";
    public static final String RESPONSE_OK = "Ok";
    private NewsEntryDBProcessor entryDBHelper;
    private NewsChannelDBProcessor channelDBHelper;

    public List<NewsEntry> getTopNewsEntries(String channel) {

        if (entryDBHelper == null) {
            entryDBHelper = new NewsEntryDBProcessor();
        }

        if (channel == null || channel.length() == 0) {
            return entryDBHelper.getTopNewsEntries();
        }

        if (channelDBHelper == null) {
            channelDBHelper = new NewsChannelDBProcessor();
        }
        List<NewsChannel> newsChannelList = channelDBHelper
                .findChannelByName(channel);

        if (newsChannelList.size() == 0) {
            return new ArrayList<NewsEntry>();
        }

        return entryDBHelper.getTopNewsEntries(newsChannelList);
    }

    public String increaseRating(String newsIdString) {

        Integer newsId = null;
        try {
            newsId = Integer.parseInt(newsIdString);
        } catch (Exception e) {
            return RESPONSE_INVALID_NEWS_ID;
        }

        if (entryDBHelper == null) {
            entryDBHelper = new NewsEntryDBProcessor();
        }

        NewsEntry newsEntry = entryDBHelper.findNewsEntryById(newsId);
        if (newsEntry == null) {
            return RESPONSE_NEWS_NOT_FOUND;
        }

        newsEntry.setRating(newsEntry.getRating() + 1);

        entryDBHelper.updateNewsEntry(newsEntry);

        String response = "News entry with id \"" + newsIdString
                + "\" now has rating " + newsEntry.getRating().toString();
        return response;
    }

    public void setEntryDBHelper(NewsEntryDBProcessor entryDBHelper) {
        this.entryDBHelper = entryDBHelper;
    }

    public void setChannelDBHelper(NewsChannelDBProcessor channelDBHelper) {
        this.channelDBHelper = channelDBHelper;
    }

}
