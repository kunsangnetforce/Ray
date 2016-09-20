package com.netforce.ray.home;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.netforce.ray.R;

public class PlayVideoActivity extends AppCompatActivity {

    VideoView video_player_view;
    DisplayMetrics dm;
    SurfaceView sur_View;
    MediaController media_Controller;
   String video_path;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);

        Intent intent = getIntent();
         video_path =  intent.getStringExtra("video_url");

        getInit();
    }


    public void getInit() {
        video_player_view = (VideoView) findViewById(R.id.video_player_view);
        media_Controller = new MediaController(PlayVideoActivity.this);
        dm = new DisplayMetrics(); this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        video_player_view.setMinimumWidth(width);
        video_player_view.setMinimumHeight(height);
        video_player_view.setMediaController(media_Controller);
        video_player_view.setVideoPath(video_path);
        video_player_view.start(); }


}
