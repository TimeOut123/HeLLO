package com.example.newsinformation.po;

import android.os.Parcel;
import android.os.Parcelable;

public class Picture {
    String id;
    String url;
    String type;
    String category;
    int likeCounts;

    public Picture() {
    }

    public Picture(String id, String url, String type, String category, int likeCounts) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.category = category;
        this.likeCounts = likeCounts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }
}
