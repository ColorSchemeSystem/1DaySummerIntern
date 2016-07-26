package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by yuji.momoi on 2016/07/15.
 */
public class LineData {
	private Paint[] line_colors;
	private int[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
	private float line_paths[][];
	private int sizes[];

	public LineData() {
		LogPrint.getInstans().logWrite("create", "LineData", true);

		this.line_colors = new Paint[colors.length];
		this.line_paths = new float[colors.length][];
		this.sizes = new int[colors.length];
		for(int i = 0;i < colors.length;i++) {
			this.line_paths[i] = new float[4];
			this.sizes[i] = 0;
			this.line_colors[i] = new Paint();
			this.line_colors[i].setColor(colors[i]);
			this.line_colors[i].setStrokeWidth(50.f);
		}

	}

	public void setLineData(Paint color, Point startPoint, Point endPoint) {
		LogPrint.getInstans().logWrite("setLineData", "color : " + color.getColor());
		int id = this.getColorId(color);

		if(id != -1) {
			float[] copy = new float[(this.sizes[id] + 1) * 4];
			System.arraycopy(this.line_paths[id], 0, copy, 0, this.line_paths[id].length);
			copy[this.sizes[id] * 4 + 0] = (float) startPoint.x;
			copy[this.sizes[id] * 4 + 1] = (float) startPoint.y;
			copy[this.sizes[id] * 4 + 2] = (float) endPoint.x;
			copy[this.sizes[id] * 4 + 3] = (float) endPoint.y;
			this.sizes[id] ++;
			this.line_paths[id] = new float[this.sizes[id] * 4];
			System.arraycopy(copy, 0 , this.line_paths[id], 0, copy.length);
		} else {
			LogPrint.getInstans().logWrite("lineData","id is none");
		}

		LogPrint.getInstans().logWrite("LineData","setLineData sX : " + startPoint.x + " sY : " + startPoint.y);
		LogPrint.getInstans().logWrite("LineData","setLineData eX : " + endPoint.x + " eY : " + endPoint.y);
	}

	public void draw(Canvas canvas) {
		canvas.save();
		for(int i = 0;i < colors.length;i++) {
			if(this.line_paths[i].length != 0)
			canvas.drawLines(this.line_paths[i], this.line_colors[i]);
		}
		canvas.restore();
	}

	private int getColorId(Paint paint) {
		int id = -1;
		for (int i = 0;i < colors.length;i++) {
			if(paint.getColor() == colors[i]) {
				id = i;
				break;
			}
		}
		return id;
	}
}