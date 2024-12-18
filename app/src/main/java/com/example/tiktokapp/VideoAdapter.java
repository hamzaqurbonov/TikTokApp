package com.example.tiktokapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<VideoItem> videoItems;
    private LifecycleOwner lifecycleOwner;

    public VideoAdapter(List<VideoItem> videoItems, LifecycleOwner lifecycleOwner) {
        this.videoItems = videoItems;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reel_desgn, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoItem videoItem = videoItems.get(position);

        lifecycleOwner.getLifecycle().addObserver(holder.youtubePlayerView);

        holder.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(videoItem.getVideoId(), 0);
            }

            public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
                if (state == PlayerConstants.PlayerState.ENDED) {
                    // Видео тугаганда қайта ўйнатиш
                    youTubePlayer.play();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        YouTubePlayerView youtubePlayerView;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            youtubePlayerView = itemView.findViewById(R.id.youtubePlayerView);
        }
    }
}

