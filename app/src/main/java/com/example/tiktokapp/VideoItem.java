package com.example.tiktokapp;

import android.util.Log;

import java.util.Random;

public class VideoItem {
    private String videoId;

    public VideoItem(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoId() {
        return videoId;
    }
}