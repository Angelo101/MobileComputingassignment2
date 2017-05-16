package com.example.noobtube.memorygame;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by noobtube on 9/05/2017.
 */

public class ViewListContents extends ActionBar {
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents);

        ListView listView = (ListView)findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);
        ArrayList<String> theList = new ArrayList<>();
        Cursor playerScores = myDB.getListContents();
        if(playerScores.getCount() == 0){// if user has not played the game yet
            Toast.makeText(this ,"No recorded scores yet!", Toast.LENGTH_SHORT).show();
        }else{//all the users scores are displayed
            while(playerScores.moveToNext()){
                theList.add(playerScores.getString(1));

            }
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(listAdapter);
        }
    }
}
