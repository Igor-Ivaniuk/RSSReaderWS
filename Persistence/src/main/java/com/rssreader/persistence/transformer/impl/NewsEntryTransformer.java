package com.rssreader.persistence.transformer.impl;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.model.impl.NewsEntry;
import com.rssreader.persistence.dao.impl.NewsChannelDAO;
import com.rssreader.persistence.dao.impl.NewsEntryDAO;
import com.rssreader.persistence.transformer.INewsTransformer;

public class NewsEntryTransformer implements
        INewsTransformer<NewsEntryDAO, NewsEntry> {

    @Override
    public NewsEntryDAO transformToDAO(NewsEntry newsEntry) {

        NewsChannelDAO newsChannelDAO = new NewsChannelDAO();

        if (newsEntry.getChannel() != null) {
            newsChannelDAO.setId(newsEntry.getChannel().getId());
            newsChannelDAO.setLink(newsEntry.getChannel().getLink());
            newsChannelDAO.setName(newsEntry.getChannel().getName());
            newsChannelDAO.setTitle(newsEntry.getChannel().getTitle());
        }
        NewsEntryDAO newsEntryDAO = new NewsEntryDAO();
        newsEntryDAO.setId(newsEntry.getId());
        newsEntryDAO.setName(newsEntry.getName());
        newsEntryDAO.setDatePublished(newsEntry.getDatePublished());
        newsEntryDAO.setRating(newsEntry.getRating());
        newsEntryDAO.setText(newsEntry.getText());
        newsEntryDAO.setChannel(newsChannelDAO);
        newsEntryDAO.setLink(newsEntry.getLink());

        return newsEntryDAO;
    }

    @Override
    public NewsEntry transformToModel(NewsEntryDAO newsEntryDAO) {

        NewsChannel newsChannel = new NewsChannel();

        if (newsEntryDAO.getChannel() != null) {
            newsChannel.setId(newsEntryDAO.getChannel().getId());
            newsChannel.setLink(newsEntryDAO.getChannel().getLink());
            newsChannel.setName(newsEntryDAO.getChannel().getName());
            newsChannel.setTitle(newsEntryDAO.getChannel().getTitle());
        }
        NewsEntry newsEntry = new NewsEntry();
        newsEntry.setId(newsEntryDAO.getId());
        newsEntry.setName(newsEntryDAO.getName());
        newsEntry.setDatePublished(newsEntryDAO.getDatePublished());
        newsEntry.setRating(newsEntryDAO.getRating());
        newsEntry.setText(newsEntryDAO.getText());
        newsEntry.setChannel(newsChannel);
        newsEntry.setLink(newsEntryDAO.getLink());

        return newsEntry;
    }

}
