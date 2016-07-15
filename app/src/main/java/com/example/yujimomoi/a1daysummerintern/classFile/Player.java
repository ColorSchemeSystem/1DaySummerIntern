package com.example.yujimomoi.a1daysummerintern.classFile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import com.example.yujimomoi.a1daysummerintern.ActionManager;
import com.example.yujimomoi.a1daysummerintern.Manager;
import com.example.yujimomoi.a1daysummerintern.R;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Player extends BaseObject {
	private static final int MOVE_MAX = 5;
	private static final int TURN_MAX = 5;
	private Point point;
	private Point old_point;
	public int rotation;
	public int texture_type;
	public int texture_color;
	private Bitmap texture;
	private Manager manager;
	private Matrix matrix;

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
		this.old_point = new Point();
		this.texture = Manager.getTexture(R.drawable.car_sample001);
		this.matrix = new Matrix();
		this.matrix.postScale(1.0f, 1.0f);
		super.setObj(this);
		Log.d("Player","width : " + this.texture.getWidth());
		Log.d("Player","height : " + this.texture.getHeight());
		Log.d("init_end", "Player");
	}

	@Override
	public void update() {
	};

	@Override
	public void draw(Canvas canvas) {
		if(this.texture != null) {
			canvas.save();
			canvas.drawBitmap(this.texture, this.matrix, new Paint());
			canvas.restore();
		} else {
		}
	}

	public Point getPoint() {
		return this.point;
	}

	public void setPoint(double pointX, double pointY) {
		this.point.x = pointX;
		this.point.y = pointY;
		this.matrix.setTranslate((float)this.point.x, (float)this.point.y);
		this.matrix.postRotate(this.rotation);
	}

	public void setPoint(Point point) {
		this.point = point;
		this.matrix.reset();
		this.matrix.setTranslate((float)this.point.x, (float)this.point.y);
		this.matrix.postRotate(this.rotation);
	}

	public int getRotation() {
		return this.rotation;
	}

	public void changeTexColor() {}

	public static void changeAllTexColor() {}

	public void move(int amountOfMove) {
		this.old_point = new Point(this.point);
		if(ActionManager.getWriteAction()) {
			while(amountOfMove > 0) {
				if(amountOfMove >= MOVE_MAX) {
					this.manager.setData(this.id + " move " + MOVE_MAX + " false");
				} else {
					this.manager.setData(this.id + " move " + amountOfMove + " false");
				}
				amountOfMove -= MOVE_MAX;
			}
		} else {
			this.point = Calcu.calcuPoint(this.point, 90 - this.rotation, amountOfMove);
			this.matrix.postTranslate((float)(this.point.x - this.old_point.x), (float)(this.point.y - this.old_point.y));
		}
	}

	public void moveWithLine(int amountOfMove) {
		this.old_point = new Point(this.point);
		if(ActionManager.getWriteAction()) {
			while(amountOfMove > 0) {
				if(amountOfMove >= MOVE_MAX) {
					this.manager.setData(this.id + " move " + MOVE_MAX + " true");
				} else {
					this.manager.setData(this.id + " move " + amountOfMove + " true");
				}
				amountOfMove -= MOVE_MAX;
			}
		} else {
			this.point = Calcu.calcuPoint(this.point, 90 - this.rotation, amountOfMove);
			this.matrix.postTranslate((float)(this.point.x - this.old_point.x), (float)(this.point.y - this.old_point.y));
		}
	}

	public void turn(int degree) {
		if(ActionManager.getWriteAction()) {
			while(degree > 0) {
				if(degree >= TURN_MAX) {
					this.manager.setData(this.id + " turn " + TURN_MAX);
				} else {
					this.manager.setData(this.id + " turn " + degree);
				}
				degree -= TURN_MAX;
			}
		} else {
			this.rotation += degree;
			if (this.rotation > 360) this.rotation -= 360;
			if (this.rotation < 0) this.rotation += 360;
//			Log.d("Player","posX : " + this.point.x);
//			Log.d("Player","posY : " + this.point.y);
//			Log.d("Player","centerX : " + this.point.x);
//			Log.d("Player","centerY : " + this.point.y);
			this.matrix.postTranslate(-(float)this.point.x - (this.texture.getWidth() / 2.0f), -(float)this.point.y - (this.texture.getHeight() / 2.0f));
			this.matrix.postRotate(degree);
			this.matrix.postTranslate((float)this.point.x + (this.texture.getWidth() / 2.0f), (float)this.point.y + (this.texture.getHeight() / 2.0f));
		}
	}
}