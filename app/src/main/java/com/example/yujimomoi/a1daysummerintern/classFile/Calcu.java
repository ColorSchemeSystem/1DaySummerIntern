package com.example.yujimomoi.a1daysummerintern.classFile;

import android.util.FloatMath;
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
	};
}