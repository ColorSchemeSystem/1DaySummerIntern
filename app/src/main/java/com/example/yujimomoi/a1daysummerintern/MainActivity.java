package com.example.yujimomoi.a1daysummerintern;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends Activity implements Runnable {
	private Handler handler;
	private Manager manager = null;
	private ViewRendere viewRendere;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		handler = new Handler();
		//setContentView(R.layout.activity_main);

		viewRendere = new ViewRendere(this);

		this.manager = new Manager(viewRendere);
		this.manager.init();
		viewRendere.setManager(manager);

		setContentView(viewRendere);
		handler.postDelayed(this, 60 / 1000);
	}

	@Override
	public void run() {
		if(this.manager != null) manager.update();
		handler.postDelayed(this, 60 / 1000);
	}
}