package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.ArrayList;

/**
 * Created by yuji.momoi on 2016/07/15.
 */
public class LineData {
	private ArrayList<Paint> line_color;
	private ArrayList<Point> start_line;
	private ArrayList<Point> end_line;
	private int size;

	public LineData() {
		LogPrint.getInstans().logWrite("create", "LineData", true);
		this.line_color = new ArrayList<Paint>();
		this.start_line = new ArrayList<Point>();
		this.end_line = new ArrayList<Point>();
		this.size = 0;
	}

	public void setLineData(Paint color, Point startPoint, Point endPoint) {
		Paint line = new Paint();
		line.setColor(color.getColor());
		line.setStrokeWidth(color.getStrokeWidth());
		LogPrint.getInstans().logWrite("setLineData", "color : " + color.getColor());
		this.line_color.add(line);
		this.start_line.add(startPoint);
		this.end_line.add(endPoint);
		this.size ++;
		LogPrint.getInstans().logWrite("LineData","setLineData sX : " + startPoint.x + " sY : " + startPoint.y);
		LogPrint.getInstans().logWrite("LineData","setLineData eX : " + endPoint.x + " eY : " + endPoint.y);
	}

	public void draw(Canvas canvas) {
		canvas.save();
		if(this.start_line.size() == this.end_line.size()) {
			int i = 0;
			for (Point startPoint : this.start_line) {
				Point endPoint = this.end_line.get(i);
				canvas.drawLine((float)startPoint.x, (float)startPoint.y, (float)endPoint.x, (float)endPoint.y, this.line_color.get(i));
				//Paint paint = this.line_color.get(i);
				//canvas.drawRect((float)startPoint.x - paint.getTextScaleX() / 2.0f, (float)startPoint.y - paint.getTextScaleX() / 2.0f, (float)endPoint.x + paint.getTextScaleX() / 2.0f, (float)endPoint.y + paint.getTextScaleX() / 2.0f, this.line_color.get(i));
				i++;
			}
		}
		canvas.restore();
	}
}
