package com.example.tiktokapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;
import android.widget.Button;
//import android.widget.TextView;

import com.example.tiktokapp.databinding.ActivityMainBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;
    ActivityMainBinding binding;
//    ArrayList<Model> arrayList = new ArrayList<>();
    Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.youtube_player_view);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] videoIds = {"6JYIGclVQdw", "LvetJ9U_tVY"};
//        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.b));
//        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.a1));

        adapter = new Adapter(MainActivity.this,videoIds);
        binding.viewpager3.setAdapter(adapter);
    }

}