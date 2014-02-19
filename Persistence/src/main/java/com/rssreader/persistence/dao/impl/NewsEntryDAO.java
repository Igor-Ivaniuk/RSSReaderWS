package com.rssreader.persistence.dao.impl;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Calendar;
import javax.persistence.*;

import com.rssreader.persistence.dao.BaseDAO;

/**
 * Entity implementation class for Entity: NewsEntry
 * 
 */

@Entity
@Table(name = "news_entry")
public class NewsEntryDAO extends BaseDAO implements Serializable {

    private static final long serialVersionUID = 440995000553730076L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private Integer rating;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datePublished;
    @Lob
    private String text;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "channel")
    private NewsChannelDAO channel;
    private String link;

    public NewsEntryDAO() {
        super();
    }

    public NewsEntryDAO(String name, Integer rating, Calendar datePublished,
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

    public NewsChannelDAO getChannel() {
        return channel;
    }

    public void setChannel(NewsChannelDAO channel) {
        this.channel = channel;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
