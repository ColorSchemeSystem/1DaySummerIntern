package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class FieldArea extends BaseObject{
	private int field_width;
	private int field_height;
	private LineData line_data;

	public FieldArea() {
		super(BaseObject.OBJ_TYPE_FIELD);
		Log.d("create", "FieldArea");
		this.field_width = 0;
		this.field_height = 0;
		this.line_data = null;
	}

	public void init(int width,int height) {
		Log.d("init", "FieldArea");
		this.field_width = width;
		this.field_height = height;
	}

	public void init() {
		Log.d("init", "FieldArea");
		this.line_data = new LineData();
		super.setObj(this);
	}

	public FieldArea getFieldSise() {
		return null;
	}

	public int getFieldWidth() {
		return this.field_width;
	}

	public int getFieldHeight() {
		return this.field_height;
	}

	public void getFieldSize() {}

	public void draw(Canvas canvas) {
		//Log.d("draw","FieldArea");
		this.line_data.draw(canvas);
	}

	public void setLineData(String lineColor, Point startPoint, Point endPoint) {
		this.line_data.setLineData(lineColor,startPoint, endPoint);
	}
}