package com.rssreader.feeddownload.parser;

import org.junit.Test;

import com.rssreader.feeddownload.entities.Feed;
import com.rssreader.feeddownload.entities.FeedMessage;

public class RSSFeedParserTest {

    @Test
    public void shouldGetFeed() {
        RSSFeedParser parser = new RSSFeedParser(
                "http://www.vogella.de/article.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }
    }

}
