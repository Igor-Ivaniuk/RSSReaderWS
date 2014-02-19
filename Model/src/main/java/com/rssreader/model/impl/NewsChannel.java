package com.rssreader.model.impl;

import java.io.Serializable;

import com.rssreader.model.BaseModel;

public class NewsChannel extends BaseModel implements Serializable {

    private static final long serialVersionUID = 2691374828551259254L;

    private int id;
    private String link;
    private String name;
    private String title;

    public NewsChannel() {
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