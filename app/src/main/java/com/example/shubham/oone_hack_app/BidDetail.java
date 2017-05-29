package com.example.shubham.oone_hack_app;

import com.google.gson.annotations.SerializedName;

/**
 * Created by shubham on 28/5/17.
 */

public class BidDetail {
    @SerializedName("adv_date")
    String date;
    int location_id;
    int slot;
    int duration;
    int bid;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public BidDetail(String date, int location_id, int slot, int duration) {
        this.date = date;
        this.location_id = location_id;
        this.slot = slot;
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
