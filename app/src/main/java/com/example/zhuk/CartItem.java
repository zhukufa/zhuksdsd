package com.example.zhuk;

import java.util.List;

public class CartItem {
    private String id;
    private String name;
    private String photourl;
    private String price;
    private String userid;

    public CartItem() {
    }

    public CartItem(String id, String name, String photourl, String price, String userid) {
        this.id = id;
        this.name = name;
        this.photourl = photourl;
        this.price = price;
        this.userid = userid;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
