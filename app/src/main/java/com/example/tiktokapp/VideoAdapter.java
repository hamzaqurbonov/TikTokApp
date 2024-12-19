package com.example.tiktokapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private final List<VideoItem> videoItems;
    private final LifecycleOwner lifecycleOwner;
    private YouTubePlayer currentPlayer;

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
        if (holder.youTubePlayer != null) {
            holder.youTubePlayer.cueVideo(videoItem.getVideoId(), videoItem.getPlaybackPosition());
            if (position == 0 && currentPlayer == null) {
                holder.youTubePlayer.play();
                currentPlayer = holder.youTubePlayer;
            }
            return;
        }

        View customPlayerUi = holder.youtubePlayerView.inflateCustomPlayerUi(R.layout.panel);
        lifecycleOwner.getLifecycle().addObserver(holder.youtubePlayerView);
        holder.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                holder.youTubePlayer = youTubePlayer;
                youTubePlayer.cueVideo(videoItem.getVideoId(), videoItem.getPlaybackPosition());

                if (position == 0 && currentPlayer == null) {
                    Log.d("YouTubePlayer", "First video is being played.");
                    youTubePlayer.play();
                    currentPlayer = youTubePlayer;
                }

                CustomPlayerUiController customPlayerUiController = new CustomPlayerUiController(customPlayerUi.getContext(), customPlayerUi, youTubePlayer, holder.youtubePlayerView);
                youTubePlayer.addListener(customPlayerUiController);
            }

            @Override
            public void onCurrentSecond(@NonNull YouTubePlayer youTubePlayer, float second) {
                videoItem.setPlaybackPosition(second); // Жорий вақтини сақлаш
            }
        });
    }


    @Override
    public int getItemCount() {
        return videoItems.size();
    }



    public void playVideoAtPosition(int position, RecyclerView recyclerView) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childView = recyclerView.getChildAt(i);
            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(childView);

            if (holder instanceof VideoViewHolder) {
                VideoViewHolder videoHolder = (VideoViewHolder) holder;

                if (recyclerView.getChildAdapterPosition(childView) == position) {
                    if (videoHolder.youTubePlayer != null) {
                        videoHolder.youTubePlayer.play();
                        currentPlayer = videoHolder.youTubePlayer;
                    }
                } else {
                    if (videoHolder.youTubePlayer != null) {
                        videoHolder.youTubePlayer.pause();
                    }
                }
            }
        }
    }


    static class VideoViewHolder extends RecyclerView.ViewHolder {
        YouTubePlayerView youtubePlayerView;
        YouTubePlayer youTubePlayer;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            youtubePlayerView = itemView.findViewById(R.id.youtubePlayerView);
        }
    }
    @Override
    public void onViewRecycled(@NonNull VideoViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder.youTubePlayer != null) {
            holder.youTubePlayer.pause();
        }
    }

}

