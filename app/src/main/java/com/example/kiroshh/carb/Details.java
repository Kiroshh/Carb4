package com.example.kiroshh.carb;

/**
 * Created by DELL on 10/29/2017.
 */

public class Details {

    private String type;
    private String time;
    private String date;

    public Details(String name, String time,String date) {
        this.type = name;
        this.time = time;
        this.date=date;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }



}