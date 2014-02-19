package com.rssreader.ws.xmlentities;

import java.util.List;

import com.rssreader.ws.xmlentities.entries.NewsEntriesXMLResponseEntry;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("news")
public class NewsEntriesXMLResponse {

    @XStreamImplicit
    private List<NewsEntriesXMLResponseEntry> newsEntriesList;

    public List<NewsEntriesXMLResponseEntry> getNewsEntriesList() {
        return newsEntriesList;
    }

    public void setNewsEntriesList(
            List<NewsEntriesXMLResponseEntry> newsEntriesList) {
        this.newsEntriesList = newsEntriesList;
    }

}
