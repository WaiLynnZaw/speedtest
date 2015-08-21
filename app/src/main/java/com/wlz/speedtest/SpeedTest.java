package com.wlz.speedtest;

import java.util.Random;

/**
 * Created by WaiLynnZaw on 8/16/15.
 */
public class SpeedTest {
    float max_speed = (float) 3.0;
    float max_ping = (float) 300.0;

    public float getDownloadSpeed(){
        Random random = new Random();
        return random.nextFloat() * max_speed;
    }

    public float getUploadSpeed(){
         return getDownloadSpeed();
    }

    public float getPing(){
        Random random = new Random();
        return random.nextFloat() * max_ping;
    }
}
