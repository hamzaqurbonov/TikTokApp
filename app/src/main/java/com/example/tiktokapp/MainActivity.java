package com.example.tiktokapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private List<VideoItem> videoItems;
    private int currentPlayingPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // SnapHelper қўшиш
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        // Видеолар рўйхати
        videoItems = new ArrayList<>();
        videoItems.add(new VideoItem("cm8x4uR1bck")); // YouTube видеонинг ID
        videoItems.add(new VideoItem("d4oXtedx67c"));
        videoItems.add(new VideoItem("tgbNymZ7vqY"));
        videoItems.add(new VideoItem("yyCB1Q_ikoE"));
        videoItems.add(new VideoItem("DN26toUeoUI"));
        videoItems.add(new VideoItem("zrXsPRCMt7s"));
        videoItems.add(new VideoItem("vK4IR8EsNEM"));
        videoItems.add(new VideoItem("mYXY36EtrnI"));
        videoItems.add(new VideoItem("WElGNoxC7h8"));
        videoItems.add(new VideoItem("8bEc1-e0HPg"));

        videoAdapter = new VideoAdapter(videoItems, this);
        recyclerView.setAdapter(videoAdapter);


        // Илк видеони автоматик ўйнаш
        recyclerView.post(() -> videoAdapter.playVideoAtPosition(0, recyclerView));

        // Скролл ҳисоби орқали автоматик видео ўйнаш
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int position = layoutManager.findFirstCompletelyVisibleItemPosition();

                    if (position != RecyclerView.NO_POSITION && position != currentPlayingPosition) {
                        currentPlayingPosition = position;
                        videoAdapter.playVideoAtPosition(position, recyclerView);
                    }
                }
            }
        });
    }


}

