package com.example.yujimomoi.a1daysummerintern.classFile;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Point {
	public double x;
	public double y;

	public Point() {
		this.x = 0;
		this.y = 0;
	}

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point point) {
		this.x = point.x;
		this.y = point.y;
	}

	public void SetPoint(double x,double y) {
		this.x = x;
		this.y = y;
	}
}