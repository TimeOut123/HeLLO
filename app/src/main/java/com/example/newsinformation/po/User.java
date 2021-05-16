package com.example.newsinformation.po;

import java.io.Serializable;

public class User implements Serializable {
    private Integer uid;
    private String uname;
    private String upassword;
    private Integer usex;
    private String uphone;

    public Integer getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public Integer getUsex() {
        return usex;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public void setUsex(Integer usex) {
        this.usex = usex;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public User() {
    }

    public User(Integer uid, String uname, String upassword, Integer usex, String uphone) {
        this.uid = uid;
        this.uname = uname;
        this.upassword = upassword;
        this.usex = usex;
        this.uphone = uphone;
    }
}
