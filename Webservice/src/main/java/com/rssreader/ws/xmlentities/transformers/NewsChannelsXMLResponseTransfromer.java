package com.rssreader.ws.xmlentities.transformers;

import java.util.ArrayList;
import java.util.List;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.ws.xmlentities.NewsChannelsXMLResponse;
import com.rssreader.ws.xmlentities.entries.NewsChannelsXMLResponseEntry;

public class NewsChannelsXMLResponseTransfromer {

    public NewsChannelsXMLResponse transfrormToNewsChannelsXMLResponse(
            List<NewsChannel> newsChannelList) {

        NewsChannelsXMLResponse response = new NewsChannelsXMLResponse();
        
        List<NewsChannelsXMLResponseEntry> ncResponseEntryList = new ArrayList<NewsChannelsXMLResponseEntry>();
        
        for (NewsChannel nc : newsChannelList) {
            NewsChannelsXMLResponseEntry ncResponseEntry = new NewsChannelsXMLResponseEntry();
            ncResponseEntry.setId(nc.getName());
            ncResponseEntry.setTitle(nc.getTitle());
            ncResponseEntryList.add(ncResponseEntry);
        }
        
        response.setNewsChannelList(ncResponseEntryList);

        return response;
    }

}
