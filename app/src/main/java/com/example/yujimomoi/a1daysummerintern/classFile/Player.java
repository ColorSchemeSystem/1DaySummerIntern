package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Canvas;
import android.util.Log;

import com.example.yujimomoi.a1daysummerintern.classFile.Point;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Player {
    public Point point;
    public int rotation;
    public int texture_type;
    public int texture_color;

    public Player() {
        Log.d("create", "Player");
        this.point = null;
        this.rotation = 0;
        this.texture_type = 0;
        this.texture_color = 0;
    };


    public void init() {
        Log.d("init", "Player");
        this.point = new Point();
    };

    public void update() {};

    public void draw() {};

    public Point getPoint() {
        return this.point;
    };

    public int getRotation() {
        return this.rotation;
    };

    public void changeTexColor() {};

    public static void changeAllTexColor() {};

    public void move(int amountOfMove) {};

    public void moveWithLine(int amountOfMove) {};

    public void turn(int degree) {};
}