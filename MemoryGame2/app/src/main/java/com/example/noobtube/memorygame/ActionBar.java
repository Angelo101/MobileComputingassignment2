package com.example.noobtube.memorygame;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActionBar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ((item.getItemId())){
            case R.id.settings:
                Intent intent = new Intent(ActionBar.this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.scores:
                Intent intentScores = new Intent(ActionBar.this, ViewListContents.class);
                startActivity(intentScores);
                return true;
            case R.id.playgame:
                Intent intentPlay = new Intent(ActionBar.this, Game4x4Activity.class);
                startActivity(intentPlay);
                return true;
            case R.id.menu:
                Intent intentMenu = new Intent(ActionBar.this, MenuActivity.class);
                startActivity(intentMenu);
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}

