package com.example.zhuk;

import java.util.List;

public class User {
    private String id;
    private String email;
    private String avtrurl;
    private List<String> cart;

    public User() {
    }

    public User(String id, String email, String avtrurl, List<String> cart) {
        this.id = id;
        this.email = email;
        this.avtrurl = avtrurl;
        this.cart = cart;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvtrurl() {
        return avtrurl;
    }

    public void setAvtrurl(String avtrurl) {
        this.avtrurl = avtrurl;
    }

    public List<String> getCart() {
        return cart;
    }

    public void setCart(List<String> cart) {
        this.cart = cart;
    }
}