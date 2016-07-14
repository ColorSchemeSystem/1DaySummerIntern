package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.yujimomoi.a1daysummerintern.ActionManager;
import com.example.yujimomoi.a1daysummerintern.Manager;
import com.example.yujimomoi.a1daysummerintern.R;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Player implements BaseObject {
	public Point point;
	public int rotation;
	public int texture_type;
	public int texture_color;
	private Bitmap texture;
	private ActionManager actionManager;
	private static int max_id = 0;
	private int id;

	public Player(ActionManager actionManager) {
		Log.d("create", "Player");
		this.point = null;
		this.rotation = 0;
		this.texture_type = 0;
		this.texture_color = 0;
		this.texture = null;
		this.actionManager = actionManager;
		this.id = max_id;
		max_id ++;
	}

	@Override
	public void init() {
		Log.d("init", "Player");
		this.point = new Point();
		this.texture = Manager.getTexture(R.drawable._app_icon_dameo);
		Log.d("init_end", "Player");
	}

	@Override
	public void update() {};

	@Override
	public void draw(Canvas canvas) {
		if(this.texture != null) {
			canvas.drawBitmap(this.texture, this.point.x, this.point.y, new Paint());
		} else {
		}
	}

	public Point getPoint() {
		return this.point;
	}

	public int getRotation() {
		return this.rotation;
	}

	public void changeTexColor() {}

	public static void changeAllTexColor() {}

	public void move(int amountOfMove) {
		double moveX = Math.sin(rotation) * amountOfMove;
		double moveY = -Math.cos(rotation) * amountOfMove;
		if(ActionManager.getWriteAction()) {
			this.actionManager.setData("player " + this.id + " move " + amountOfMove);
		} else {
			this.point.x += moveX;
			this.point.y += moveY;
		}
	}

	public void moveWithLine(int amountOfMove) {
		double moveX = Math.sin(rotation) * amountOfMove;
		double moveY = -Math.cos(rotation) * amountOfMove;
		this.point.x += moveX;
		this.point.y += moveY;
	}

	public void turn(int degree) {
		if(ActionManager.getWriteAction()) {
			this.actionManager.setData("player " + this.id + " turn " + degree);
		} else {
			this.rotation += degree;
			if (this.rotation > 360) this.rotation -= 360;
			if (this.rotation < 0) this.rotation += 360;
		}
	}
}