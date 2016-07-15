package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuji.momoi on 2016/07/15.
 */
public class LineData {
	private Map<String, Color> line_color;
	private Map<String, String> line_point;
	private int size;

	public LineData() {
		this.line_color = new HashMap<String, Color>();
		this.line_point = new HashMap<String, String>();
		this.size = 0;
	}

	public void setLineData(Color color, Point startPoint, Point endPoint) {
		this.line_color.put(String.valueOf(this.size), color);
		String pointData = String.valueOf(startPoint.x) + " " +
							String.valueOf(startPoint.y) + " " +
							String.valueOf(endPoint.x) + " " +
							String.valueOf(endPoint.y);
		this.line_point.put(String.valueOf(this.size), pointData);
		this.size ++;
	}
}
