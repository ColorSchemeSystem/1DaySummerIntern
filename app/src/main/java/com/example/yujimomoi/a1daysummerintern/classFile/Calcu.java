package com.example.yujimomoi.a1daysummerintern.classFile;

import android.util.Log;

import java.math.BigDecimal;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Calcu {
	public static int calcuRotation(double rad) {
		return 0;
	};

	public static Point calcuPoint(Point nowPoint, int rotation, int amountOfMove) {
		Double rad = rotation * Math.PI / 180;
		nowPoint.x += Math.cos(rad) * amountOfMove;
		nowPoint.y -= Math.sin(rad) * amountOfMove;
		return nowPoint;
//		BigDecimal x = new BigDecimal(nowPoint.x);
//		BigDecimal y = new BigDecimal(nowPoint.y);
//		BigDecimal amountX = new BigDecimal(Math.sin(rotation) * amountOfMove);
//		BigDecimal amountY = new BigDecimal(-Math.cos(rotation) * amountOfMove);
//		Point point = new Point();
//		point.x = x.add(amountX).doubleValue();
//		point.y = y.add(amountY).doubleValue();
//		return point;
	};
}