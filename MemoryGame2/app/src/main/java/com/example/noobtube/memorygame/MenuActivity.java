package com.example.noobtube.memorygame;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.view.GestureDetectorCompat;
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
    private static final int AUTHENTICATE = 1;
    TextView textView;
    Twitter twitter = TwitterFactory.getSingleton();
    private GestureDetectorCompat gestureObject;


    private Button play;
    private Button settings;
    private Button scores;
    private Button post;
    private Context context;
    SettingsActivity settingsActivity;
//    public MediaPlayer mediaPlayer =null;
    public static int bg;





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
        } else{
            setContentView(R.layout.activity_menu);
            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }
    }
        gestureObject = new GestureDetectorCompat(this, new LearnGesture());

        post =(Button)findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PostToTwitter.postToTwitter();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
//                Intent intent = new Intent(context, Authenticate.class);
//                startActivityForResult(intent, AUTHENTICATE);

            }
        });
        textView = (TextView)findViewById(R.id.text_view);
        textView.setMovementMethod(new ScrollingMovementMethod());
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
                startActivity(intent);


            }
        });
    }
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObject.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            if(e2.getX() > e1.getX()){
                Intent intent = new Intent(context, SettingsActivity.class);
                startActivity(intent);
            }else{
                if(e2.getX() < e1.getX()){

                }
            }

            return true;
        }
    }
}
