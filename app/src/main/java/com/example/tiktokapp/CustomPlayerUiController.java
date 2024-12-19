package com.example.tiktokapp;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;


import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class CustomPlayerUiController extends AbstractYouTubePlayerListener {

    private final Context context;
    private final YouTubePlayer youTubePlayer;
    private View panel;

    public    CustomPlayerUiController(Context context, View customPlayerUi, YouTubePlayer youTubePlayer, YouTubePlayerView youTubePlayerView) {
        this.context = context;
        this.youTubePlayer = youTubePlayer;
        initViews(customPlayerUi);
    }

    private void initViews(View playerUi) {
        panel = playerUi.findViewById(R.id.panel);

        if (panel == null) {
            Log.d("demo50", "initViews null");
        }
    }

    @Override
    public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
        if (state == PlayerConstants.PlayerState.ENDED) {
            // Видео тугаганда қайта ўйнатиш
            playVideoAtSelection();
        }
//        playVideoAtSelection();
        if (panel != null) { // panel `null` эмаслигини текшириш
            if (state == PlayerConstants.PlayerState.PLAYING ||
                    state == PlayerConstants.PlayerState.PAUSED ||
                    state == PlayerConstants.PlayerState.VIDEO_CUED) {

                panel.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
            } else if (state == PlayerConstants.PlayerState.BUFFERING) {
                panel.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent));
            }
        } else {
            Log.d("demo50", "onStateChange null");
        }
    }
    private void playVideoAtSelection() {
        if (!(youTubePlayer == null)) {
            youTubePlayer.play();
        }
    }

}