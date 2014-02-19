package com.rssreader.persistence.dao.impl;

import java.io.Serializable;
import javax.persistence.*;

import com.rssreader.persistence.dao.BaseDAO;

/**
 * The persistent class for the news_channel database table.
 * 
 */
@Entity
@Table(name = "news_channel")
public class NewsChannelDAO extends BaseDAO implements Serializable {

    private static final long serialVersionUID = -4311849859346455277L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String link;
    private String name;
    private String title;

    public NewsChannelDAO() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}