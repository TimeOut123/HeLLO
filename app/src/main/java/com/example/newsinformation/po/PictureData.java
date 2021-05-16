package com.example.newsinformation.po;

import android.media.Image;

import java.util.List;

public class PictureData {
    List<Picture> pictureList;
    int pageCount;
    int pageSize;

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
