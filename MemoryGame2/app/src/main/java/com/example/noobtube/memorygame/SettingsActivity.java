package com.example.noobtube.memorygame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends ActionBar {

    public Button buttonOrginal;
    public Button changeBackground;
    public Context context;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context = this;
        changeBackground = (Button) findViewById(R.id.changeBackground);
        buttonOrginal = (Button) findViewById(R.id.buttonOriginal);
        changeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //changing the background image of menu
                MenuActivity.backgroundCount +=1;
                Intent intent = new Intent(context, MenuActivity.class);
                startActivity(intent);
            }
        });
        buttonOrginal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// changing the background back
                MenuActivity.backgroundCount = 0;
                Intent intent = new Intent(context, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}