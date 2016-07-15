package com.example.yujimomoi.a1daysummerintern;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.example.yujimomoi.a1daysummerintern.classFile.Player;

import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

	private Handler handler = new Handler();
	private Runnable updateText;
	private TimerTask timerTask;

	private long FPS = 60 / 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);

		ViewRendere viewRendere = new ViewRendere(this);

		final Manager manager = new Manager(viewRendere);
		manager.init();
		viewRendere.setManager(manager);

		setContentView(viewRendere);
	}
}