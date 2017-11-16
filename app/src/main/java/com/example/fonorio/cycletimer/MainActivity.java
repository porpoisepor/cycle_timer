package com.example.fonorio.cycletimer;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

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
    Timer timer;
    long period = 1000;
    long startingDelay = 0;
    boolean started = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mainButton = (Button) findViewById(R.id.mainButton);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        mainButton.setOnClickListener((v)->{
            Button button = (Button) v;
            if(!started){
                started = true;
                timer = new Timer();
                timer.schedule(wrapLambda(() -> {
                    System.out.println("Lambda task ran.");
                    vibrator.vibrate(100);
                }), startingDelay, period);
                button.setBackgroundColor(R.color.initialButtonColor);
                button.setText(R.string.initialButtonText);
            } else {
                started = false;
                button.setBackgroundColor(R.color.secondaryStateButtonColor);
                button.setText(R.string.secondaryStateButtonText);
                timer.cancel();
                timer = null;
            }
        });
    }
}
