package com.example.newsinformation.po;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class News implements Parcelable {
    private String _id;
    private String author;
    private String title;
    private String publishedAt;
    private String desc;
    private String url;

    public News(String _id, String author, String title, String publishedAt, String desc, String url) {
        this._id = _id;
        this.author = author;
        this.title = title;
        this.publishedAt = publishedAt;
        this.desc = desc;
        this.url = url;
    }

    public News() {
    }

    protected News(Parcel in) {
        _id = in.readString();
        author = in.readString();
        title = in.readString();
        publishedAt = in.readString();
        desc = in.readString();
        url = in.readString();
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }



    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @NonNull
    @Override
    public String toString() {
        return "News{"+
                "_id="+_id+
                ", author='"+author+'\''+
                ", publishedAt='"+ publishedAt +'\''+
                ", desc='"+desc+'\''+
                ", title='"+title+'\''+
                ", url='"+url+'\''+
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(author);
        parcel.writeString(publishedAt);
        parcel.writeString(desc);
        parcel.writeString(title);
        parcel.writeString(url);
    }
    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            // 必须按成员变量声明的顺序读取数据，不然会出现获取数据出错
            News news = new News();
            news.set_id(in.readString());
            news.setAuthor(in.readString());
            news.setPublishedAt(in.readString());
            news.setDesc(in.readString());
            news.setTitle(in.readString());
            news.setUrl(in.readString());
            return news;
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };
}
