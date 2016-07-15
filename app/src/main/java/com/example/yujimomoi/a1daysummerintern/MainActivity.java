package com.example.yujimomoi.a1daysummerintern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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