package com.rssreader.model.impl;

import java.io.Serializable;
import java.util.Calendar;

import com.rssreader.model.BaseModel;

public class NewsEntry extends BaseModel implements Serializable {

    private static final long serialVersionUID = 3137704899659756210L;

    private int id;
    private String name;
    private Integer rating;
    private Calendar datePublished;
    private String text;
    private NewsChannel channel;
    private String link;

    public NewsEntry() {
        super();
    }

    public NewsEntry(String name, Integer rating, Calendar datePublished,
            String text) {
        this.name = name;
        this.rating = rating;
        this.datePublished = datePublished;
        this.text = text;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Calendar getDatePublished() {
        return this.datePublished;
    }

    public void setDatePublished(Calendar datePublished) {
        this.datePublished = datePublished;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public NewsChannel getChannel() {
        return channel;
    }

    public void setChannel(NewsChannel channel) {
        this.channel = channel;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
