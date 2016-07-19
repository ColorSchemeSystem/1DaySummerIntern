package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.graphics.Color;
import com.example.yujimomoi.a1daysummerintern.ActionManager;
import com.example.yujimomoi.a1daysummerintern.Manager;
import com.example.yujimomoi.a1daysummerintern.R;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Player extends BaseObject {
	private static final int MOVE_MAX = 10;
	private static final float TURN_MAX = 10.0f;
	private Point point;
	private Point old_point;
	private float rotation;
	private int texture_type;
	private int texture_color;
	private Bitmap texture;
	private Manager manager;
	private Matrix matrix;
	private Paint line;

	public Player(Manager manager) {
		super(BaseObject.OBJ_TYPE_PLAYER);
		LogPrint.getInstans().logWrite("create", "Player", true);
		this.point = null;
		this.rotation = 0;
		this.texture_type = 0;
		this.texture_color = 0;
		this.texture = null;
		this.line = null;
		this.manager = manager;
	}

	@Override
	public void init() {
		LogPrint.getInstans().logWrite("init", "Player", true);
		this.point = new Point();
		this.old_point = new Point();
		this.texture = Manager.getTexture(R.drawable.car_sample001);
		this.matrix = new Matrix();
		this.line = new Paint();
		this.line.setStyle(Paint.Style.STROKE);
		this.line.setStrokeWidth(20.f);
		this.matrix.postScale(1.0f, 1.0f);
		super.setObj(this);
		LogPrint.getInstans().logWrite("Player","width : " + this.texture.getWidth());
		LogPrint.getInstans().logWrite("Player","height : " + this.texture.getHeight());
		LogPrint.getInstans().logWrite("init_end", "Player", true);
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
		Point point = new Point(this.point.x + this.texture.getWidth() / 2.0f, this.point.y + this.texture.getHeight() / 2.0f);
		return point;
	}

	public Point getOldPoint() {
		Point point = new Point(this.old_point.x + this.texture.getWidth() / 2.0f, this.old_point.y + this.texture.getHeight() / 2.0f);
		return point;
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

	public float getRotation() {
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
			this.point = Calcu.calcuPoint(this.point, 90.0f - this.rotation, amountOfMove);
			this.matrix.postTranslate((float)(this.point.x - this.old_point.x), (float)(this.point.y - this.old_point.y));
			this.manager.setLineData(new Paint(this.line),this.getPoint(),this.getOldPoint());
		}
	}

	public void turn(float degree) {
		if(ActionManager.getWriteAction()) {
			float absDegree = Math.abs(degree);
			while(absDegree > 0) {
				if(absDegree >= TURN_MAX) {
					if(degree < 0) {this.manager.setData(this.id + " turn " + -TURN_MAX);}
					else this.manager.setData(this.id + " turn " + TURN_MAX);
				} else {
					if(degree < 0) this.manager.setData(this.id + " turn " + -absDegree);
					else this.manager.setData(this.id + " turn " + absDegree);
				}
				absDegree -= TURN_MAX;
			}
		} else {
			this.rotation += degree;
			if (this.rotation > 360) this.rotation -= 360;
			if (this.rotation < 0) this.rotation += 360;
			this.matrix.postTranslate(-(float)this.point.x - (this.texture.getWidth() / 2.0f), -(float)this.point.y - (this.texture.getHeight() / 2.0f));
			this.matrix.postRotate(degree);
			this.matrix.postTranslate((float)this.point.x + (this.texture.getWidth() / 2.0f), (float)this.point.y + (this.texture.getHeight() / 2.0f));
		}
	}

	public void setTexture(int textureId) {
		this.texture = Manager.getTexture(textureId);
	}

	public void setLineColor(int color) {
		if(ActionManager.getWriteAction()) {
			this.manager.setData(this.id + " lineColor " + String.valueOf(color));
		} else {
			this.line.setColor(color);
		}
	}

	public void setLineSize(float lineSize) {
		if(ActionManager.getWriteAction()) {
			this.manager.setData(this.id + " lineSize " + String.valueOf(lineSize));
		} else {
			this.line.setStrokeWidth(lineSize);
		}
	}
}