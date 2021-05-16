package com.example.newsinformation.po;

import java.io.Serializable;

public class NewsContent implements Serializable {
    private int id,uid;
    private String url;

    public int getId() {
        return id;
    }

    public int getUid() {
        return uid;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsContent{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
