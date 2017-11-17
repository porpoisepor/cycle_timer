package com.example.fonorio.cycletimer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static TimerTask wrapLambda(Runnable runnable){
        return new TimerTask(){
            @Override
            public void run() {
                runnable.run();
            }
        };
    }
    private Timer timer;
    private long period = 600; // crashes //Integer.parseInt(getString(R.string.startingNumberOfSeconds));
    private long startingDelay = 0;
    private boolean started;
    public final String sharedPreferencesFilename = "cycleTimerPreferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // System.out.println("onCreate started");
        // System.out.println("started: " + started);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFilename, MODE_PRIVATE);
        started = sharedPreferences.getBoolean("hasStarted", false);
        Button mainButton = (Button) findViewById(R.id.mainButton);
        updateButton(mainButton);
        mainButton.setOnClickListener((v)->{
            Button button = (Button) v;
            // System.out.println("Button has been clicked.");
            if(!started){
                startTask(button);
            } else {
                stopTask(button);
            }
            // System.out.println("started: " + started);
        });
        // System.out.println("onCreate finished");
        // System.out.println("started: " + started);
    }
    private void startTask(Button button) {
        started = true;
        timer = new Timer();
        EditText numberInput = findViewById(R.id.numberInput);
        period = Integer.parseInt(numberInput.getText().toString());
        timer.schedule(wrapLambda(() -> {
            // System.out.println("Lambda task ran.");
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(new long[]{0,300,1000,300,1000,300,1000,300,1000,300}, -1);
//            vibrator.vibrate(1*60*1000);
        }), startingDelay, period*1000);
        updateButton(button);
    }
    private void stopTask(Button button) {
        started = false;
        if(timer != null){
            timer.cancel();
            timer = null;
        }
        updateButton(button);
    }
    private void updateButton(Button button){
        if(!started){
            button.setBackgroundResource(R.drawable.rounded_background);
            button.setText(R.string.initialButtonText);
        } else {
            button.setBackgroundResource(R.drawable.secondary_state_rounded_background);
            button.setText(R.string.secondaryStateButtonText);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFilename, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("hasStarted", false);
        editor.commit();
        stopTask((Button) findViewById(R.id.mainButton));
        // System.out.println("onDestroy called");
    }
    @Override
    protected void onPause() {
        super.onPause();
        // System.out.println("calling onPause");
        // System.out.println("started: " + started);
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFilename, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("hasStarted", started);
        editor.commit();
        // System.out.println("onPause called");
        // System.out.println("started: " + started);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // System.out.println("calling onResume ");
        // System.out.println("started: " + started);
        SharedPreferences sharedPreferences = getSharedPreferences(sharedPreferencesFilename, MODE_PRIVATE);
        started = sharedPreferences.getBoolean("hasStarted", false);
        updateButton(findViewById(R.id.mainButton));
        // System.out.println("onResume called");
        // System.out.println("started: " + started);
    }
    @Override
    protected void onStop() {
        super.onStop();

        // System.out.println("onStop called");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        // System.out.println("onRestart called");
    }
    @Override
    protected void onStart() {
        super.onStart();
        // System.out.println("onStart called");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putBoolean("hasStarted", started);
        // System.out.println("onSaveInstanceState called");
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        started = savedInstanceState.getBoolean("hasStarted");
        // System.out.println("onRestoreInstanceState called");
    }
}
