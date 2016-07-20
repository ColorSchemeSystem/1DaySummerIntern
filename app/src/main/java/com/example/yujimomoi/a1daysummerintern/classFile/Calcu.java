package com.example.yujimomoi.a1daysummerintern.classFile;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Calcu {
	public static float pow(float amount) {
		return amount * amount;
	}

	public static float pow(double amount) {
		return (float) (amount * amount);
	}

	public static Double calcuRadian(float degree) {
		return degree * Math.PI / 180;
	};

	public static Double calcuDegree(float radian) {
		return radian * 180 / Math.PI;
	}

	public static Point calcuPoint(Point nowPoint, float rotation, float amountOfMove) {
		Double rad = calcuRadian(rotation);
		nowPoint.x += Math.cos(rad) * amountOfMove;
		nowPoint.y -= Math.sin(rad) * amountOfMove;
		return nowPoint;
	};

	public static float getDistanse(Point start, Point end) {
		float distance = (float) Math.sqrt(pow(end.x - start.x) + pow(end.y - start.y));
		return distance;
	}

	public static float getRadian(Point start, Point end) {
		float radian = (float) Math.atan2((end.y - start.y), (end.x - start.x));
		return radian;
	}
}