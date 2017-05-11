package com.example.noobtube.memorygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        TextView instructions = (TextView) findViewById(R.id.instructions);
        instructions.setText("How to Play:\n Tap on the icons and find all the pairs! \n \n" +
                "after 2 icons are selected you have 0.2ms to \nmemorize what the icons were before they " +
                "flip back over!");
    }
}
