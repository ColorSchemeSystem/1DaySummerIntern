package com.example.yujimomoi.a1daysummerintern;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

import com.example.yujimomoi.a1daysummerintern.classFile.Point;

public class MainActivity extends Activity implements Runnable {
	private Handler handler;
	private Manager manager = null;
	private ViewRendere viewRendere;
	private Point old_point;
	private Point point;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.point = new Point();
		this.old_point = new Point();
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

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			{
				old_point.x = event.getX();
				old_point.y = event.getY();
			}
			break;
			case MotionEvent.ACTION_UP:
			{
			}
			break;
			case MotionEvent.ACTION_MOVE:
			{
				point.x = event.getX();
				point.y = event.getY();
				viewRendere.scrollBy((int)(old_point.x - point.x), (int)(old_point.y - point.y));
				old_point.x = event.getX();
				old_point.y = event.getY();
			}
			break;
		}
		return true;
	}
}