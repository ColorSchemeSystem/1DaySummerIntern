package com.example.yujimomoi.a1daysummerintern.classFile;

import android.util.Log;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class FieldArea {
	private int field_width;
	private int field_height;
	private String line_color;

	public FieldArea() {
		Log.d("create", "FieldArea");
		field_width = 0;
		field_height = 0;
		line_color = null;
	}

	public void init(int width,int height) {
		Log.d("init", "FieldArea");
		field_width = width;
		field_height = height;
	}

	public void init() {

		Log.d("init", "FieldArea");
	}

	public FieldArea getFieldSise() {
		return null;
	}

	public int getFieldWidth() {
		return field_width;
	}

	public int getFieldHeight() {
		return field_height;
	}

	public void getFieldSize() {}

	public void drawLine(Point point) {}
}