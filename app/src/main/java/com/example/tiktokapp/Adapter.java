package com.example.tiktokapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktokapp.databinding.ReelDesgnBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
public class Adapter extends RecyclerView.Adapter<Adapter.VideoHolder> {
    Context context;
    ArrayList<Model>modelArrayList;
    private final String[] videoIds;

//    public Adapter(Context context, ArrayList<Model> modelArrayList, String[] videoIds) {
//        this.context = context;
//        this.modelArrayList = modelArrayList;
//        this.videoIds = videoIds;
//    }

//    MainActivity context;
    public Adapter(MainActivity context, String[] videoIds) {
        this.context = context;
        this.modelArrayList = modelArrayList;
        this.videoIds = videoIds;
    }

//    public Adapter(MainActivity context, String[] videoIds) {
//    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.reel_desgn,parent,false);
        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) LayoutInflater.from(parent.getContext()).inflate(R.layout.reel_desgn, parent, false);
        return new VideoHolder(youTubePlayerView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        holder.loadVideo(videoIds[position]);
//        holder.binding.videoView.setVideoPath(modelArrayList.get(position).getVideoUrl());

//        holder.binding.name.setText(modelArrayList.get(position).getName());
//        holder.binding.profileImage.setImageResource(modelArrayList.get(position).getProfile());

//        holder.binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mediaPlayer) {
//                mediaPlayer.start();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return videoIds.length;
    }

    public class VideoHolder extends RecyclerView.ViewHolder{
        private final YouTubePlayerView youTubePlayerView;
        private YouTubePlayer youTubePlayer;
        private String currentVideoId;
        ReelDesgnBinding binding;
        public VideoHolder(YouTubePlayerView youTubePlayerView) {
            super(youTubePlayerView);
            this.youTubePlayerView = youTubePlayerView;
            binding = ReelDesgnBinding.bind(itemView);

            this.youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer initializedYouTubePlayer) {
                    youTubePlayer = initializedYouTubePlayer;
                    youTubePlayer.loadVideo(currentVideoId, 0);
                }
            });

        }
        void loadVideo(String videoId) {
            currentVideoId = videoId;

            if (youTubePlayer == null)
                return;

            youTubePlayer.loadVideo(videoId, 0);
        }
    }

}
