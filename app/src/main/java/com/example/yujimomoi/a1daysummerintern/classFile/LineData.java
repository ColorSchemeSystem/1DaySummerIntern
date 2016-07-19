package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuji.momoi on 2016/07/15.
 */
public class LineData {
	private Map<String, String > line_color;
	private ArrayList<Point> start_line;
	private ArrayList<Point> end_line;
	private int size;

	public LineData() {
		this.line_color = new HashMap<String, String>();
		this.start_line = new ArrayList<Point>();
		this.end_line = new ArrayList<Point>();
		this.size = 0;
	}

	public void setLineData(String color, Point startPoint, Point endPoint) {
		this.line_color.put(String.valueOf(this.size), color);
		this.start_line.add(startPoint);
		this.end_line.add(endPoint);
		this.size ++;
		Log.d("LineData","setLineData sX : " + startPoint.x + " sY : " + startPoint.y);
		Log.d("LineData","setLineData eX : " + endPoint.x + " eY : " + endPoint.y);
	}

	public void draw(Canvas canvas) {
		canvas.save();
		//Log.d("LineData","size : " + this.size);
		if(this.start_line.size() == this.end_line.size()) {
			int i = 0;
			for (Point startPoint : this.start_line) {
				Point endPoint = this.end_line.get(i);
				canvas.drawLine((float)startPoint.x, (float)startPoint.y, (float)endPoint.x, (float)endPoint.y, new Paint());
				i++;
			}
		}
		canvas.restore();
	}
}
