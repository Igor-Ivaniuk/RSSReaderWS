package com.rssreader.persistence.processor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.rssreader.model.impl.NewsChannel;
import com.rssreader.model.impl.NewsEntry;
import com.rssreader.persistence.processor.NewsEntryDBProcessor;

public class DBRunner {

    /**
     * @param args
     */
    public static void main(String[] args) {
         fillEntries();
        // testSelectAll();
        // testSelectByChannel();
        // findEntry();
    }

    private static void findEntry() {
        NewsEntryDBProcessor nedbp = new NewsEntryDBProcessor();
        NewsEntry ne = nedbp.findNewsEntryById(2255);
        ne.setRating(ne.getRating() + 1);
//        nedbp = new NewsEntryDBProcessor();
        nedbp.updateNewsEntry(ne);
        System.out.println(ne);
    }

    private static void testSelectByChannel() {
        NewsEntryDBProcessor nedbp = new NewsEntryDBProcessor();
        List<NewsChannel> newsChannelList = new ArrayList<NewsChannel>();
        NewsChannel nc = new NewsChannel();
        nc.setName("habr");
        nc.setId(70);
        newsChannelList.add(nc);
        NewsChannel nc2 = new NewsChannel();
        nc2.setName("korr");
        nc2.setId(69);
        newsChannelList.add(nc2);
        List<NewsEntry> nelist = nedbp.getTopNewsEntries(newsChannelList);
        nelist.size();
    }

    private static void testSelectAll() {
        NewsEntryDBProcessor nedbp = new NewsEntryDBProcessor();
        List<NewsEntry> nelist = nedbp.getTopNewsEntries();

    }

    private static void fillEntries() {

        NewsEntryDBProcessor nedbp = new NewsEntryDBProcessor();

        Random randomGenerator = new Random();
        List<NewsEntry> nel = new ArrayList<NewsEntry>();

        NewsChannel nc = new NewsChannel();
        nc.setLink("http://k.img.com.ua/rss/ru/mainbyday.xml");
        nc.setName("korr");
        nc.setTitle("Korrespondent.Net");

        for (int i = 1; i <= 150; i++) {
            NewsEntry ne = new NewsEntry();
            ne.setChannel(nc);
            ne.setRating(randomGenerator.nextInt(4) + 1);
            ne.setName("Korr" + ne.getRating().toString());
            Calendar datePublished = Calendar.getInstance();
            datePublished.add(Calendar.SECOND, randomGenerator.nextInt(9) + 1);
            ne.setDatePublished(datePublished);
            ne.setLink("http://korr/" + randomGenerator.nextInt(99));
            nel.add(ne);
        }

        List<NewsEntry> nel2 = new ArrayList<NewsEntry>();

        NewsChannel nc2 = new NewsChannel();
        nc2.setLink("http://habrahabr.ru/rss/main/");
        nc2.setName("habr");
        nc2.setTitle("Habrahabr.ru");

        for (int i = 1; i <= 150; i++) {
            NewsEntry ne = new NewsEntry();
            ne.setChannel(nc2);
            ne.setRating(randomGenerator.nextInt(4) + 1);
            ne.setName("Habr" + ne.getRating().toString());
            Calendar datePublished = Calendar.getInstance();
            datePublished.add(Calendar.SECOND, randomGenerator.nextInt(9) + 1);
            ne.setDatePublished(datePublished);
            ne.setLink("http://habr.ru/" + randomGenerator.nextInt(99));
            nel2.add(ne);
        }

        nedbp.createMultipleNewsEntriesSameChannel(nel);
        nedbp.createMultipleNewsEntriesSameChannel(nel2);

    }

}
