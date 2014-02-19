package com.rssreader.ws.xmlentities.transformers;

import java.util.ArrayList;
import java.util.List;

import com.rssreader.model.impl.NewsEntry;
import com.rssreader.ws.xmlentities.NewsEntriesXMLResponse;
import com.rssreader.ws.xmlentities.entries.NewsEntriesXMLResponseEntry;

public class NewsEntriesXMLResponseTransfromer {

    public NewsEntriesXMLResponse transfrormToNewsEntriesXMLResponse(
            List<NewsEntry> newsEntries) {
        NewsEntriesXMLResponse response = new NewsEntriesXMLResponse();

        List<NewsEntriesXMLResponseEntry> newsEntriesXMLResponseEntries = new ArrayList<NewsEntriesXMLResponseEntry>();

        for (NewsEntry ne : newsEntries) {
            NewsEntriesXMLResponseEntry neResponseEntry = new NewsEntriesXMLResponseEntry();
            neResponseEntry.setChannel(ne.getChannel().getName());
            neResponseEntry.setId(Integer.toString(ne.getId()));
            neResponseEntry.setLink(ne.getLink());
            neResponseEntry.setName(ne.getName());
            neResponseEntry.setRank(ne.getRating());
            newsEntriesXMLResponseEntries.add(neResponseEntry);
        }

        response.setNewsEntriesList(newsEntriesXMLResponseEntries);

        return response;
    }

}
