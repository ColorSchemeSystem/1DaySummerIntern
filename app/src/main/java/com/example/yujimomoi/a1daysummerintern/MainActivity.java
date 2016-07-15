package com.example.yujimomoi.a1daysummerintern;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

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