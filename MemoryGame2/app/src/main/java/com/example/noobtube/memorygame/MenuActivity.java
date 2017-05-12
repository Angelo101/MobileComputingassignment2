package com.example.noobtube.memorygame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.SensorEventListener;
import android.os.StrictMode;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class MenuActivity extends  AppCompatActivity {
//    private static final int AUTHENTICATE = 1;
    TextView textView, swipeLeft;
//    Twitter twitter = TwitterFactory.getSingleton();
    public GestureDetectorCompat gestureObject;


    private Button play;
    private Button settings;
    private Button scores;
    private Button post;
    private Context context;
    SettingsActivity settingsActivity;
//    public MediaPlayer mediaPlayer =null;
    public static int bg;
    public static int sensorChanged = 0;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        if (bg == 1) {
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


//        textView = (TextView) findViewById(R.id.text_view);
//        textView.setMovementMethod(new ScrollingMovementMethod());

        swipeLeft = (TextView) findViewById(R.id.swipeLeft);
        swipeLeft.setText("PLANET MEMORY GAME!\n" +
                "\nSWIPE LEFT TO VIEW GAME INSTRUCTIONS");
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());

        post = (Button) findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        play = (Button) findViewById(R.id.button_4x4_game);
        play.setText("PLAY");


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Game4x4Activity.class);
                startActivity(intent);


            }
        });
    }
        public boolean onTouchEvent(MotionEvent event){
            this.gestureObject.onTouchEvent(event);
            return super.onTouchEvent(event);
        }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


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
                if(e2.getX() < e1.getX()){

                }
            }

            return true;
        }
    }
}
