package com.example.fonorio.cycletimer;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask(){
        @Override
        public void run() {
            System.out.println("timer task " + timerTask.toString() + " has completed.");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mainButton = (Button) findViewById(R.id.mainButton);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mainButton.setOnClickListener((v)->{
            System.out.println("mainButton clicked");
            vibrator.vibrate(500);
        });
        timer.schedule(timerTask, 0, 3000);
    }
}
