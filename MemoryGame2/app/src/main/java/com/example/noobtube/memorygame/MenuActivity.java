package com.example.noobtube.memorygame;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button play;
    private Button settings;
    private Button scores;
    private Context context;
    SettingsActivity settingsActivity;
//    public MediaPlayer mediaPlayer =null;
    public static int bg;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (bg == 1) {
            setContentView(R.layout.activity_menu2);
        } else{
            setContentView(R.layout.activity_menu);
    }
        context = this;
        settings = (Button)findViewById(R.id.settings);
        scores = (Button)findViewById(R.id.scores);

        scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewListContents.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SettingsActivity.class);
                startActivity(intent);


            }
        });

        play = (Button)findViewById(R.id.button_4x4_game);
        play.setText("PLAY");


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Game4x4Activity.class);
//                music();
                startActivity(intent);


            }
        });




    }
    public static void changeBackground(){

    }



//    public void music(){
////        mediaPlayer = MediaPlayer.create(MenuActivity.this, R.raw.thinking);
//        MediaPlayer mediaPlayer = null;
//        if (mediaPlayer == null){
//            mediaPlayer = MediaPlayer.create(MenuActivity.this, R.raw.thinking);
//            mediaPlayer.start();
//        }
////        else{
////            mediaPlayer.stop();
////            mediaPlayer=null;
////        }
//    }

}
