package com.example.noobtube.memorygame;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    public Button off;
    public Button on;
    public MediaPlayer mediaPlayer = null;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        on = (Button) findViewById(R.id.on);
        off = (Button) findViewById(R.id.off);
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music();

            }
        });

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music();
            }
        });

    }
    public void music(){
        if (mediaPlayer ==null){
            mediaPlayer = MediaPlayer.create(SettingsActivity.this, R.raw.thinking);
            mediaPlayer.start();
        }
        else{
            mediaPlayer.stop();
            mediaPlayer=null;
        }
        }

}