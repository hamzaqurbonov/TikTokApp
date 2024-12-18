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
        videoItems.add(new VideoItem("dQw4w9WgXcQ")); // YouTube видеонинг ID
        videoItems.add(new VideoItem("EDOWDqn8zpw"));
        videoItems.add(new VideoItem("tgbNymZ7vqY"));
        videoItems.add(new VideoItem("yyCB1Q_ikoE"));
        videoItems.add(new VideoItem("DN26toUeoUI"));
        videoItems.add(new VideoItem("R1AWAy62PxM"));


        videoAdapter = new VideoAdapter(videoItems, this);
        recyclerView.setAdapter(videoAdapter);

        // Скролл ҳисоби орқали автоматик видео ўйнаш
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Ҳар бир позиция учун видео юкланиши
                        VideoItem currentVideo = videoItems.get(position);
                        System.out.println("Текущий видео: " + currentVideo.getVideoId());
                    }
                }
            }
        });
    }
}
