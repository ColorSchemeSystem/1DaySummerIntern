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
public class Player extends BaseObject {
	public Point point;
	public int rotation;
	public int texture_type;
	public int texture_color;
	private Bitmap texture;
	private Manager manager;

	public Player(Manager manager) {
		super(BaseObject.OBJ_TYPE_PLAYER);
		Log.d("create", "Player");
		this.point = null;
		this.rotation = 0;
		this.texture_type = 0;
		this.texture_color = 0;
		this.texture = null;
		this.manager = manager;
	}

	@Override
	public void init() {
		Log.d("init", "Player");
		this.point = new Point();
		this.texture = Manager.getTexture(R.drawable._app_icon_dameo);
		super.setObj(this);
		Log.d("init_end", "Player");
	}

	@Override
	public void update() {
	};

	@Override
	public void draw(Canvas canvas) {
		if(this.texture != null) {
			canvas.drawBitmap(this.texture, (float)this.point.x, (float)this.point.y, new Paint());
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
//		this.point.x += Math.sin(this.rotation) * amountOfMove;
//		this.point.y += -Math.cos(this.rotation) * amountOfMove;
		if(ActionManager.getWriteAction()) {
			this.manager.setData(this.id + " move " + String.valueOf(amountOfMove) + " false");
			for(int i = 0;i < amountOfMove;i++) {
				this.manager.setData(this.id + " move 1 false");
			}
		} else {
			this.point = Calcu.calcuPoint(this.point, this.rotation, amountOfMove);
		}
	}

	public void moveWithLine(int amountOfMove) {
		if(ActionManager.getWriteAction()) {
//			this.manager.setData(this.id + " move " + String.valueOf(amountOfMove) + " false");
			for(int i = 0;i < amountOfMove;i++) {
				this.manager.setData(this.id + " move 1 false");
			}
		} else {
			this.point = Calcu.calcuPoint(this.point, this.rotation, amountOfMove);
		}
	}

	public void turn(int degree) {
//		this.rotation += degree;
//		if (this.rotation > 360) this.rotation -= 360;
//		if (this.rotation < 0) this.rotation += 360;
		if(ActionManager.getWriteAction()) {
			this.manager.setData(this.id + " turn " + degree);
		} else {
			this.rotation += degree;
			if (this.rotation > 360) this.rotation -= 360;
			if (this.rotation < 0) this.rotation += 360;
		}
	}
}