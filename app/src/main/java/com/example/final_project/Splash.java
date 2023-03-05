package com.example.final_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.databinding.SplashActivityBinding;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    SplashActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=SplashActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                // TODO Auto-generated method stub

                SharedPreferences SP = getSharedPreferences(Register.PREF_NAME, MODE_PRIVATE);

                if (SP.getBoolean(Register.RememberK, false)) {
                    startActivity(new Intent(getBaseContext(), Rules.class));
                } else {
                    startActivity(new Intent(getBaseContext(), Login.class));
                }

                finish();
            }}, 4000);

        binding.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        SharedPreferences SP = getSharedPreferences(Register.PREF_NAME, MODE_PRIVATE);

                        if (SP.getBoolean(Register.RememberK, false)) {
                            startActivity(new Intent(getBaseContext(), Rules.class));
                        } else {
                            startActivity(new Intent(getBaseContext(), Login.class));
                        }

            }
        });
    }
}