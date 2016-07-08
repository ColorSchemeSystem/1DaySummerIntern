package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.yujimomoi.a1daysummerintern.R;
import com.example.yujimomoi.a1daysummerintern.classFile.Point;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Player implements BaseObject {
    public Point point;
    public int rotation;
    public int texture_type;
    public int texture_color;
    private Bitmap texture;
    private Point texture_size;

    public Player() {
        Log.d("create", "Player");
        this.point = null;
        this.rotation = 0;
        this.texture_type = 0;
        this.texture_color = 0;
        this.texture = null;
        this.texture_size = null;
    };

    @Override
    public void init() {
        Log.d("init", "Player");
        this.point = new Point();
        this.point = new Point(100, 100);
        //this.texture = BitmapFactory.decodeResource(getResources(), R.drawable._app_icon_dameo);
    };

    @Override
    public void update() {};

    @Override
    public void draw(Canvas canvas) {

    };

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