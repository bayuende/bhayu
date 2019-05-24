package com.bhayu.app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    @SerializedName("news")
    @Expose
    private java.util.List<News> news;

    public java.util.List<News> getNews() {
        return news;
    }

    public void setAll(java.util.List<News> all) {
        this.news = news;
    }

}
