package com.example.noobtube.memorygame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.GridLayout;
import java.util.Random;
import android.os.Handler;
import android.widget.Toast;

public class Game4x4Activity extends AppCompatActivity implements SearchView.OnClickListener, SensorEventListener{

    private int numberOfElements;
    private MemoryButton[] buttons;
    private int [] buttonGraphicLocations;// this will hold a ranomized list of the button locations
    private int [] buttongGraphics; // the picture of the buttons
    private MemoryButton selectedButton1;
    private boolean isBusy = false; //this is so we don't crash the app so  users cannot spam click all the buttons
    private MemoryButton selectedButton2;
    public MediaPlayer mp;
    public int pairsFound = 0;
    public int incrementClicks = 0;
    public int finalCount;
    static int twitterScore;
    private Sensor myAccelerometer;
    private SensorManager SM;

    private long lastUpdate = 0;
    private float last_x,last_y,last_z;
    private static final int SHAKE_THRESHOLD = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4x4);
        Toast.makeText(this ," TIP: SHAKE DEVICE TO RESTART GAME!", Toast.LENGTH_SHORT).show();
        mp = MediaPlayer.create(Game4x4Activity.this, R.raw.thinking);
        mp.start();
        SM = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        // Accelerometer Sensor
        myAccelerometer = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor Listener
        SM.registerListener(this, myAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);


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
            incrementClicks++;
            return;
        }
        if(selectedButton1.getFrontDrawableId() == button.getFrontDrawableId()){
            button.flip();
            button.setMatched(true);
            pairsFound++;
            incrementClicks++;
            if(pairsFound == 8){ // if all pairs are found
                finalCount = incrementClicks;
                twitterScore = finalCount;
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                // Adds score to db
                saveScore(finalCount);
                alert.setMessage("You completed the game in " + incrementClicks + "clicks!")
                        .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(Game4x4Activity.this, MenuActivity.class);
                                startActivity(intent);
                            }
                        })
                        .create();
                alert.show();
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
            incrementClicks++;
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
    protected void onPause(){
        super.onPause();
        SM.unregisterListener(this);
    }
    protected void onResume(){
        super.onResume();
        SM.registerListener(this, myAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if(mySensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if((curTime - lastUpdate)>100){
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                float speed =Math.abs(x+y+z - last_x -last_y -last_z)/ diffTime *10000;

                if(speed > SHAKE_THRESHOLD){
                    Intent intent = new Intent(Game4x4Activity.this, Game4x4Activity.class); //when device is shaken it restarts the game
                    startActivity(intent);
                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
