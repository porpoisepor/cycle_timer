package com.example.fonorio.cycletimer;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

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
    }
}
