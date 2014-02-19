package com.rssreader.ws.xmlbuilders;

import java.util.List;

import com.rssreader.model.impl.NewsEntry;
import com.rssreader.ws.xmlentities.NewsEntriesXMLResponse;
import com.rssreader.ws.xmlentities.entries.NewsEntriesXMLResponseEntry;
import com.rssreader.ws.xmlentities.transformers.NewsEntriesXMLResponseTransfromer;
import com.thoughtworks.xstream.XStream;

public class NewsEntriesXMLResponseBuilder {

    private static final String NO_NEWS_FOUND = "<news>No news found</news>";
    private NewsEntriesXMLResponseTransfromer transformer = new NewsEntriesXMLResponseTransfromer();

    public String buildResponse(List<NewsEntry> newsEntries) {
        
        if (newsEntries == null || newsEntries.size() == 0) {
            return NO_NEWS_FOUND;
        }

        NewsEntriesXMLResponse response = transformer
                .transfrormToNewsEntriesXMLResponse(newsEntries);
        XStream xstream = new XStream();
        xstream.processAnnotations(NewsEntriesXMLResponse.class);
        xstream.processAnnotations(NewsEntriesXMLResponseEntry.class);

        return xstream.toXML(response);
    }

    public void setTransformer(NewsEntriesXMLResponseTransfromer transformer) {
        this.transformer = transformer;
    }

}
