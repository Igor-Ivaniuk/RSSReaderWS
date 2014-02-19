package com.rssreader.ws.jersey;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.rssreader.business.NewsEntriesBL;
import com.rssreader.model.impl.NewsEntry;
import com.rssreader.ws.xmlbuilders.NewsEntriesXMLResponseBuilder;

@Path("/top/news")
public class TopNewsResource {

    private static final String RESPONSE_NOT_IMPLEMENTED = "Not implemented";

    private NewsEntriesBL helper = new NewsEntriesBL();
    private NewsEntriesXMLResponseBuilder xmlBuilder = new NewsEntriesXMLResponseBuilder();

    @GET
    @Produces({ "text/xml" })
    public String getNewsChannels(@QueryParam("channel") String channel) {

        List<NewsEntry> newsEntriesList = helper.getTopNewsEntries(channel);

        try {
            String response = xmlBuilder.buildResponse(newsEntriesList);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @PUT
    @Produces("text/plain")
    @Path("{news_id}/")
    public String addRating(@PathParam("news_id") String newsId,
            @QueryParam("up") String up) {
        if (up != null) {
            String response = helper.increaseRating(newsId);
            return response;
        }
        return RESPONSE_NOT_IMPLEMENTED;
    }
}
