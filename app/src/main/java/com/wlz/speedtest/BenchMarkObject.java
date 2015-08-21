package com.wlz.speedtest;

import io.realm.RealmObject;

/**
 * Created by WaiLynnZaw on 8/16/15.
 */
public class BenchMarkObject extends RealmObject {
    private int hour;
    private String date;
    private float upload_speed;
    private float download_speed;
    private float ping;

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getUpload_speed() {
        return upload_speed;
    }

    public void setUpload_speed(float upload_speed) {
        this.upload_speed = upload_speed;
    }

    public float getDownload_speed() {
        return download_speed;
    }

    public void setDownload_speed(float download_speed) {
        this.download_speed = download_speed;
    }

    public float getPing() {
        return ping;
    }

    public void setPing(float ping) {
        this.ping = ping;
    }


}
