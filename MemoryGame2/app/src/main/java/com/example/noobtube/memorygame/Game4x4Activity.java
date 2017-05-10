package com.example.noobtube.memorygame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Random;
import android.os.Handler;

public class Game4x4Activity extends AppCompatActivity implements SearchView.OnClickListener {

    private int numberOfElements;
    private MemoryButton[] buttons;
    private int [] buttonGraphicLocations;// this will hold a ranomized list of the button locations
    private int [] buttongGraphics; // the picture of the buttons
    private MemoryButton selectedButton1;
    private boolean isBusy = false; //this is so we don't crash the app so  users cannot spam click all the buttons
    private MemoryButton selectedButton2;
    public MediaPlayer mp;
    public int clickCount = 0;
    public int finish = 0;
    public int finalCount;
    static int twitterScore;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4x4);
        mp = MediaPlayer.create(Game4x4Activity.this, R.raw.thinking);
        mp.start();
         if(SensorForGame.sensorChanged == true){
             Intent intent = new Intent(context, Game4x4Activity.class);
             startActivity(intent);

        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.activity_game4x4);

        int numColumns = gridLayout.getColumnCount();
        int numRows = gridLayout.getRowCount();
        numberOfElements = numColumns * numRows;
        buttons = new MemoryButton[numberOfElements];
        buttongGraphics = new int[numberOfElements /2];
        buttongGraphics[0] = R.drawable.button1;
        buttongGraphics[1] = R.drawable.button2;
        buttongGraphics[2] = R.drawable.button3;
        buttongGraphics[3] = R.drawable.button4;
        buttongGraphics[4] = R.drawable.button5;
        buttongGraphics[5] = R.drawable.button6;
        buttongGraphics[6] = R.drawable.button7;
        buttongGraphics[7] = R.drawable.button8;
        buttonGraphicLocations = new int[numberOfElements];

        shuffleButtonGraphics();
        for(int rows = 0; rows < numRows; rows++){
            for(int columns = 0; columns < numColumns; columns++){
                MemoryButton tempButton = new MemoryButton(this, rows, columns, buttongGraphics[buttonGraphicLocations[rows * numColumns + columns]]);
                tempButton.setId(View.generateViewId());
                tempButton.setOnClickListener(this);
                buttons[rows * numColumns + columns]= tempButton;
                gridLayout.addView(tempButton);
            }
        }

    }
    protected void shuffleButtonGraphics(){
        Random rand = new Random();
        for(int j = 0; j < numberOfElements; j++){
            buttonGraphicLocations[j] = j% (numberOfElements /2);
        }
        for(int j = 0; j <numberOfElements; j++){
            int temp = buttonGraphicLocations[j];
            int swapIndex = rand.nextInt(16);
            buttonGraphicLocations[j] = buttonGraphicLocations[swapIndex];
            buttonGraphicLocations[swapIndex] = temp;
        }
    }

    @Override
    public void onClick(View v) {
        if(isBusy) return;
        MemoryButton button = (MemoryButton) v;
        if(button.isMatched)
            return;
        if(selectedButton1 == null){
            selectedButton1 = button;
            selectedButton1.flip();
            finish++;
            return;
        }
        if(selectedButton1.getFrontDrawableId() == button.getFrontDrawableId()){
            button.flip();
            button.setMatched(true);
            clickCount++;
            finish++;
            if(clickCount == 8){ // if all pairs are found
                finalCount = finish;
                twitterScore = finalCount;
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                PostToTwitter postToTwitter;

                // Adds score to db
                saveScore(finalCount);
                alert.setMessage("You completed the game in" + finish)
                        .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                alert.show();
                // Open scores page
                startActivity(new Intent(this, ViewListContents.class));
            }
            selectedButton1.setMatched(true);
            selectedButton1.setEnabled(false);
            button.setEnabled(false);
            selectedButton1 = null;
            return;
        }
         else{
            selectedButton2 = button;
            selectedButton2.flip();
            finish++;
            isBusy = true;
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    selectedButton2.flip();
                    selectedButton1.flip();
                    selectedButton1 = null;
                    selectedButton2 = null;
                    isBusy = false;

                }
            }, 200);// very short delay to make it harder.

        }
    }
    public void onStop() {// when Game activity stops this stops the music
        super.onStop();
        mp.stop();
    }

    public void saveScore(int score){
        DatabaseHelper myDB = new DatabaseHelper(this);
        myDB.addData(String.valueOf(score));
    }
    public int theCount(){
        return finalCount;
    }

}
