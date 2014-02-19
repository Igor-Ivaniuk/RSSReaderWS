package com.rssreader.ws.xmlbuilders;

import java.util.List;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.ws.xmlentities.NewsChannelsXMLResponse;
import com.rssreader.ws.xmlentities.entries.NewsChannelsXMLResponseEntry;
import com.rssreader.ws.xmlentities.transformers.NewsChannelsXMLResponseTransfromer;
import com.thoughtworks.xstream.XStream;

public class NewsChannelsXMLResponseBuilder {

    private static final String NO_CHANNELS_FOUND = "<channels>No channels found</channels>";
    private NewsChannelsXMLResponseTransfromer transformer = new NewsChannelsXMLResponseTransfromer();

    public String buildResponse(List<NewsChannel> newsChannels) {
        
        if (newsChannels == null || newsChannels.size() == 0) {
            return NO_CHANNELS_FOUND;
        }

        NewsChannelsXMLResponse response = transformer
                .transfrormToNewsChannelsXMLResponse(newsChannels);
        XStream xstream = new XStream();
        xstream.processAnnotations(NewsChannelsXMLResponse.class);
        xstream.processAnnotations(NewsChannelsXMLResponseEntry.class);

        return xstream.toXML(response);
    }

    public void setTransformer(NewsChannelsXMLResponseTransfromer transformer) {
        this.transformer = transformer;
    }

}
