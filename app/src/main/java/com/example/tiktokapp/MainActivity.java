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

//    Model modelUrl = new Model.getNextVideoId();
    private YouTubePlayerView youTubePlayerView;


    ActivityMainBinding binding;
    ArrayList<Model> arrayList = new ArrayList<>();
    Adapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        initYouTubePlayerView();


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.b));
        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.a1));
//        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.b,R.drawable.ic_launcher_foreground,"Sveta"));
//        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.a2,R.drawable.ic_launcher_foreground,"Gulhumor"));
//        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.a3,R.drawable.ic_launcher_foreground,"Iroda"));
//        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.a4,R.drawable.ic_launcher_foreground,"Aziza"));
//        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.a5,R.drawable.ic_launcher_foreground,"Nilufar"));
//        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.a6,R.drawable.ic_launcher_foreground,"Muhabbat"));
//        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.a7,R.drawable.ic_launcher_foreground,"Setora"));
//        arrayList.add(new Model("android.resource://"+ getPackageName() + "/" + R.raw.a8,R.drawable.ic_launcher_foreground,"Ali"));
        adapter = new Adapter(MainActivity.this,arrayList);
        binding.viewpager2.setAdapter(adapter);

    }

    public void initYouTubePlayerView() {
        getLifecycle().addObserver(youTubePlayerView);

        YouTubePlayerListener listener = new AbstractYouTubePlayerListener() {

            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                setPlayNextVideoButtonClickListener(youTubePlayer);
                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer,
                        getLifecycle(),
                        "5K1OfKKEw"
                        ,
                        0f

                );
//                Log.d("demo15", getNextVideoId());
            }

        };

        // disable web ui
        IFramePlayerOptions options = new IFramePlayerOptions.Builder().controls(0).build();

        youTubePlayerView.initialize(listener, options);
    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.matchParent();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            youTubePlayerView.wrapContent();
        }
    }
    private void setPlayNextVideoButtonClickListener(final YouTubePlayer youTubePlayer) {
        ViewPager2 playNextVideoButton = findViewById(R.id.viewpager2);

        playNextVideoButton.setOnClickListener(view ->
                YouTubePlayerUtils.loadOrCueVideo(
                        youTubePlayer,
                        getLifecycle(),
                        "5K1OfKKEw",

                        0f
                )

        );
        Log.d("demo19", Model.getNextVideoId());
    }




}