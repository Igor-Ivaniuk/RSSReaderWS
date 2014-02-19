package com.rssreader.persistence.transformer.impl;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.persistence.dao.impl.NewsChannelDAO;
import com.rssreader.persistence.transformer.INewsTransformer;

public class NewsChannelTransformer implements
        INewsTransformer<NewsChannelDAO, NewsChannel> {

    @Override
    public NewsChannelDAO transformToDAO(NewsChannel newsChannel) {
        NewsChannelDAO newsChannelDAO = new NewsChannelDAO();
        newsChannelDAO.setId(newsChannel.getId());
        newsChannelDAO.setLink(newsChannel.getLink());
        newsChannelDAO.setName(newsChannel.getName());
        newsChannelDAO.setTitle(newsChannel.getTitle());
        return newsChannelDAO;
    }

    @Override
    public NewsChannel transformToModel(NewsChannelDAO newsChannelDAO) {
        NewsChannel newsChannel = new NewsChannel();
        newsChannel.setId(newsChannelDAO.getId());
        newsChannel.setLink(newsChannelDAO.getLink());
        newsChannel.setName(newsChannelDAO.getName());
        newsChannel.setTitle(newsChannelDAO.getTitle());
        return newsChannel;
    }
}
