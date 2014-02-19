package com.rssreader.ws.xmlentities;

import java.util.List;

import com.rssreader.ws.xmlentities.entries.NewsChannelsXMLResponseEntry;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("channels")
public class NewsChannelsXMLResponse {

    @XStreamImplicit
    private List<NewsChannelsXMLResponseEntry> newsChannelList;

    public List<NewsChannelsXMLResponseEntry> getNewsChannelList() {
        return newsChannelList;
    }

    public void setNewsChannelList(List<NewsChannelsXMLResponseEntry> newsChannelList) {
        this.newsChannelList = newsChannelList;
    }

}
