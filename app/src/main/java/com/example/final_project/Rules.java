package com.example.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.databinding.ActivityRulesBinding;

public class Rules extends AppCompatActivity {
    ActivityRulesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRulesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.videoView.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick( View view ) {
                VideoView videoView = (VideoView) findViewById(R.id.videoView);
                videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video2);

                MediaController mediaController = new MediaController(getApplicationContext());
                //link mediaController to videoView
                mediaController.setAnchorView(videoView);
                //allow mediaController to control our videoView
                videoView.setMediaController(mediaController);
                videoView.start();
            }
        } );


        binding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Game.class);
                startActivity(intent);
            }
        });
    }
}