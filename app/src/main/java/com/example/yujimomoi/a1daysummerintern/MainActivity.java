package com.example.yujimomoi.a1daysummerintern;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable updateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Manager manager = new Manager();
        manager.init();
        Log.d("info","まねーじゃーinit");

        updateText = new Runnable() {
            @Override
            public void run() {
                manager.update();
                manager.draw();
                handler.removeCallbacks(updateText);
                handler.postDelayed(updateText, 60 / 1000);
            }
        };
        handler.postDelayed(updateText, 60 / 1000);
    }
}