package com.example.tiktokapp;

import android.util.Log;

import java.util.Random;

public class Model {

    public static final String[] videoUrlString = {"5K1OfKKEw", "dyof-qvpcM8", "O0Y8E61c8TI", };

    public static final Random random = new Random();
    public static String getNextVideoId() {

        return videoUrlString[random.nextInt(videoUrlString.length)];
    }
    String videoUrl;
    int profile;
    String name;

    public Model(String videoUrl, int profile, String name) {
        this.videoUrl = videoUrl;
        this.profile = profile;
        this.name = name;
    }

    public String getVideoUrl() {
//        String videoUrl = getNextVideoId();
        Log.d("demo18", videoUrl);
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
