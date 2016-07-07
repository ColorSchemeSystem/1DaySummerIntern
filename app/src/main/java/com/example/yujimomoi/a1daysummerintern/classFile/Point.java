package com.example.yujimomoi.a1daysummerintern.classFile;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Point {
    public int x;
    public int y;

    public Point() {
        this.x = 0;
        this.y = 0;
    };

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    };

    public void SetPoint(int x,int y) {
        this.x = x;
        this.y = y;
    };
}