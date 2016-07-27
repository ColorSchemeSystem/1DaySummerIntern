package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class FieldArea extends BaseObject{
	private LineData line_data;

	public FieldArea() {
		super(BaseObject.OBJ_TYPE_FIELD);
		LogPrint.getInstans().logWrite("create", "FieldArea", true);
		this.line_data = null;
	}

	public void init() {
		LogPrint.getInstans().logWrite("init", "FieldArea", true);
		this.line_data = new LineData();
		super.setObj(this);
	}

	public void draw(Canvas canvas) {
		this.line_data.draw(canvas);
	}

	public void setLineData(Paint lineColor, Point startPoint, Point endPoint) {
		this.line_data.setLineData(lineColor,startPoint, endPoint);
	}
}