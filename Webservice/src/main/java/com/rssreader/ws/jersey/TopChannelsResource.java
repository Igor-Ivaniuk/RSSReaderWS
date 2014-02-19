package com.rssreader.ws.jersey;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.rssreader.business.NewsChannelsBL;
import com.rssreader.model.impl.NewsChannel;
import com.rssreader.ws.xmlbuilders.NewsChannelsXMLResponseBuilder;

@Path("/top/channels")
public class TopChannelsResource {

    private NewsChannelsBL helper = new NewsChannelsBL();
    private NewsChannelsXMLResponseBuilder xmlBuilder = new NewsChannelsXMLResponseBuilder();

    @GET
    @Produces({ "text/xml" })
    public String getNewsChannels() {

        List<NewsChannel> newsChannelList = helper.getNewsChannels();

        try {
            String response = xmlBuilder.buildResponse(newsChannelList);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}