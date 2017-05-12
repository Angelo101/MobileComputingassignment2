package com.example.noobtube.memorygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);
        ActionBar actionBar = new ActionBar();
        actionBar.setTitle("Planet Memory Game");
        actionBar.setVisible(true);
    }
}
