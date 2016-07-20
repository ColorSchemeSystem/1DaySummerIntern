package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by yuji.momoi on 2016/07/15.
 */
public class LineData {
	private Paint line_color;
	private float[] line_path;
	private int size;

	public LineData() {
		LogPrint.getInstans().logWrite("create", "LineData", true);
		this.line_color = new Paint();
		this.line_path = new float[4];
		for (int i = 0; i < 4;i++) {
			this.line_path[i] = 0.0f;
		}
		this.size = 0;
	}

	public void setLineData(Paint color, Point startPoint, Point endPoint) {
		LogPrint.getInstans().logWrite("setLineData", "color : " + color.getColor());
		this.line_color = color;
		float[] copy = new float[(this.size + 1) * 4];
		System.arraycopy(this.line_path, 0, copy, 0, this.line_path.length);
		copy[this.size * 4 + 0] = (float) startPoint.x;
		copy[this.size * 4 + 1] = (float) startPoint.y;
		copy[this.size * 4 + 2] = (float) endPoint.x;
		copy[this.size * 4 + 3] = (float) endPoint.y;
		int n = this.size;
		this.size ++;
		this.line_path = new float[this.size * 4];
		System.arraycopy(copy, 0 , this.line_path, 0, copy.length);
		LogPrint.getInstans().logWrite("LineData","setLineData sX : " + startPoint.x + " sY : " + startPoint.y);
		LogPrint.getInstans().logWrite("LineData","setLineData eX : " + endPoint.x + " eY : " + endPoint.y);
	}

	public void draw(Canvas canvas) {
		canvas.save();
		canvas.drawLines(this.line_path, this.line_color);
		canvas.restore();
	}
}