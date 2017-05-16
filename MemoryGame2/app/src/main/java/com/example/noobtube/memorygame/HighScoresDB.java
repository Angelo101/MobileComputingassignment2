package com.example.noobtube.memorygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class HighScoresDB extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText scoreField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores_db);


        scoreField = (EditText) findViewById(R.id.scoreField);
        myDB = new DatabaseHelper(this);
        String newEntry = scoreField.getText().toString();
        myDB.addData(newEntry);
        myDB.getListContents();
        System.out.println(newEntry + "Testing");

    }
    public void addData(String newEntry){
        boolean insertData = myDB.addData(newEntry);

    }
}
