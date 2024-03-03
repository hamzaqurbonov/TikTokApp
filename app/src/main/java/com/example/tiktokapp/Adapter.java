package com.example.tiktokapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktokapp.databinding.ReelDesgnBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
public class Adapter extends RecyclerView.Adapter<Adapter.VideoHolder> {
    Context context;
    ArrayList<Model>modelArrayList;
    private final String[] videoIds;

    private Lifecycle lifecycle;
    private View panel;

//    public Adapter(Context context, ArrayList<Model> modelArrayList, String[] videoIds) {
//        this.context = context;
//        this.modelArrayList = modelArrayList;
//        this.videoIds = videoIds;
//    }

//    MainActivity context;
    public Adapter(Context context, String[] videoIds, Lifecycle lifecycle) {
        this.context = context;
//        this.modelArrayList = modelArrayList;
        this.videoIds = videoIds;
        this.lifecycle = lifecycle;
    }

//    public Adapter(MainActivity context, String[] videoIds) {
//    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reel_desgn,parent,false);
//        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) LayoutInflater.from(parent.getContext()).inflate(R.layout.reel_desgn, parent, false);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView)view.findViewById(R.id.youtube_player_view);
        lifecycle.addObserver(youTubePlayerView);
//        RecyclerViewAdapter.ViewHolder vh = new RecyclerViewAdapter.ViewHolder(youTubePlayerView);
        return new VideoHolder(youTubePlayerView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHolder holder, int position) {
        holder.loadVideo(videoIds[position]);

        Log.d("demo2", holder.loadVideo(videoIds[position]));
//        holder.playVideoAtSelection();
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

    public class VideoHolder extends RecyclerView.ViewHolder {
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
                @Override
                public void onStateChange(@NonNull YouTubePlayer youTubePlayer, @NonNull PlayerConstants.PlayerState state) {
                    playVideoAtSelection();

                    // this method is called if video has ended,
                    super.onStateChange(youTubePlayer, state);
                }

        });



        }
        String loadVideo(String videoId) {
            currentVideoId = videoId;

            if (youTubePlayer == null)
                return videoId;

            youTubePlayer.loadVideo(videoId, 0);
//            youTubePlayer.play();
            return videoId;
        }

        private void playVideoAtSelection() {
            if (!(youTubePlayer == null)) {
                youTubePlayer.play();
            }
        }


    }

}
