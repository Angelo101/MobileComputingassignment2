package com.example.noobtube.memorygame;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Instructions extends AppCompatActivity {
// this class will bring an alert message
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("How to Play: Tap on the icons and find all the pairs! "+
                "                \"after 2 icons are selected you have 0.2ms to memorize what the icons were before they \" " +
                "                \"flip back over!")
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Instructions.this, MenuActivity.class);
                        startActivity(intent);
                        dialog.dismiss();
                    }
                })
                .create();
        alert.show();
    }

}
