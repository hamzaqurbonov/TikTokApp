package com.example.tiktokapp;

import android.util.Log;

import java.util.Random;

public class VideoItem {
    private String videoId;
    private float playbackPosition; // Вақтни сақлаш учун

    public VideoItem(String videoId) {
        this.videoId = videoId;
        this.playbackPosition = 0;
    }

    public String getVideoId() {
        return videoId;
    }

    public float getPlaybackPosition() {
        return playbackPosition;
    }

    public void setPlaybackPosition(float playbackPosition) {
        this.playbackPosition = playbackPosition;
    }


}
