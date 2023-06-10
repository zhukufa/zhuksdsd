package com.example.zhuk.database;

import java.util.HashSet;
import java.util.Set;

public class Monitors {
    public static Set<Integer> items_name = new HashSet<>();
    public static Set<Integer> items_price = new HashSet<>();
    public String name, price, photo1, photo2;

    public Monitors() {
    }

    public Monitors(String name, String price, String photo1, String photo2) {

        this.name = name;
        this.price = price;
        this.photo1 = photo1;
        this.photo2 = photo2;
    }
}
