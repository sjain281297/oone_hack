package com.example.shubham.oone_hack_app;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 28/5/17.
 */

public class Location {
    int id;
    @SerializedName("area_name")
    String name;

    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
