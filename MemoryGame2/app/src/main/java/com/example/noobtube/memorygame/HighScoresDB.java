package com.example.noobtube.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HighScoresDB extends AppCompatActivity {

    DatabaseHelper myDB;
    Button add,view;
    EditText editText;
    int count =10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores_db);

        editText = (EditText) findViewById(R.id.editText);
        myDB = new DatabaseHelper(this);
        String newEntry = editText.getText().toString();
        myDB.addData(newEntry);
        addData("Hello, World");
        myDB.getListContents();

    }
    public void addData(String newEntry){
        boolean insertData = myDB.addData(newEntry);

    }
}
