package com.example.noobtube.memorygame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.StrictMode;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends  ActionBar {
    TextView textView, swipeLeft;
    public GestureDetectorCompat gestureObject;
    private Button play;
    private Button settings;
    private Button scores;
    private Button post;
    private Context context;
    public static int backgroundCount;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        if (backgroundCount == 1) { //changing the background if count == 1
            setContentView(R.layout.activity_menu2);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
        } else {
            setContentView(R.layout.activity_menu);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
        }

        final MediaPlayer mp; // this is for everyhting but play game
        mp = MediaPlayer.create(this, R.raw.power);
        final MediaPlayer soundEffect; // playgame sound effect for button
        soundEffect = MediaPlayer.create(this,R.raw.timewarp);

        swipeLeft = (TextView) findViewById(R.id.swipeLeft);
        swipeLeft.setText("PLANET MEMORY GAME!\n" +
                "\nSWIPE LEFT TO VIEW GAME INSTRUCTIONS");
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());

        post = (Button) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(context, TwitterMain.class);
                startActivity(intent);
            }
        });

        textView = (TextView) findViewById(R.id.text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());
        settings = (Button) findViewById(R.id.settings);
        scores = (Button) findViewById(R.id.scores);
        scores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(context, ViewListContents.class);
                startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(context, SettingsActivity.class);
                startActivity(intent);
            }
        });

        play = (Button) findViewById(R.id.button_4x4_game);
        play.setText("PLAY");
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundEffect.start();
                Intent intent = new Intent(MenuActivity.this, Game4x4Activity.class);
                startActivity(intent);
            }
        });
    }
        public boolean onTouchEvent(MotionEvent event){
            this.gestureObject.onTouchEvent(event);
            return super.onTouchEvent(event);
        }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{ // creating gestures

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // swipe left to right
            if(e2.getX() > e1.getX()){
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setMessage("How to Play: Tap on the icons and find all the pairs!\n "+
                        "after 2 icons are selected you have 0.2ms to memorize what the icons were before they \" " +
                        "flip back over!")
                        .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        })
                        .create();
                alert.show();
            }else{
                //swipe right to left
                if(e2.getX() < e1.getX()){
                }
            }
            return true;
        }
    }
}
