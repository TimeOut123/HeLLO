package com.example.newsinformation.po;

import java.io.Serializable;

public class Newsinfo implements Serializable {
    private   int imgRes;
    private String newsTitle;
    private String newsInfo;
    public Newsinfo(int imgRes, String newsTitle, String newsInfo) {
        this.imgRes = imgRes;
        this.newsTitle = newsTitle;
        this.newsInfo = newsInfo;
    }

    public int getImgRes() {
        return imgRes;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsInfo() {
        return newsInfo;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public void setNewsInfo(String newsInfo) {
        this.newsInfo = newsInfo;
    }
    @Override
    public String toString() {
        return "NewsInfo{" +
                "imgRes=" + imgRes +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsInfo='" + newsInfo + '\'' +
                '}';
    }
}
