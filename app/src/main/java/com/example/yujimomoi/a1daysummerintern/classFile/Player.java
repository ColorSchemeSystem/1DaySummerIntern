package com.example.yujimomoi.a1daysummerintern.classFile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.graphics.Color;
import com.example.yujimomoi.a1daysummerintern.ActionManager;
import com.example.yujimomoi.a1daysummerintern.Manager;
import com.example.yujimomoi.a1daysummerintern.R;
import java.util.ArrayList;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Player extends BaseObject {
	private static final int MOVE_MAX = 10;
	private static final int TURN_MAX = 10;
	private Point point;
	private Point old_point;
	public int rotation;
	public int texture_type;
	public int texture_color;
	private Bitmap texture;
	private Manager manager;
	private Matrix matrix;
	private String status;
	private Paint paint;
	private ArrayList<Double> points;
	private float start_line_x;
	private float start_line_y;
	private float end_line_x;
	private float end_line_y;
	private String line_status;

	public Player(Manager manager) {
		super(BaseObject.OBJ_TYPE_PLAYER);
		Log.d("create", "Player");
		this.point = null;
		this.rotation = 0;
		this.texture_type = 0;
		this.texture_color = 0;
		this.texture = null;
		this.manager = manager;
		this.start_line_x = 0;
		this.start_line_y = 0;
		this.end_line_x = 0;
		this.end_line_y = 0;
		this.line_status = "sleep";
	}

	@Override
	public void init() {
		Log.d("init", "Player");
		this.point = new Point();
		this.old_point = new Point();
		this.texture = Manager.getTexture(R.drawable.car_sample001);
		this.matrix = new Matrix();
		this.status = "";
		this.matrix.postScale(1.0f, 1.0f);
		super.setObj(this);
		this.paint = new Paint();
		this.points = new ArrayList<Double> ();
		this.points.add(-10000.0);
		Log.d("Player","width : " + this.texture.getWidth());
		Log.d("Player","height : " + this.texture.getHeight());
		Log.d("init_end", "Player");
	}

	@Override
	public void update() {
		Log.d("Player","posX" + point.x);
		Log.d("Player","posY" + point.y);
	};

	@Override
	public void draw(Canvas canvas) {
		if(this.texture != null) {
			canvas.save();
			switch (this.status){
				case "move":
				{
					Log.d("draw", "MOVE");
					Bitmap bitmap = Bitmap.createScaledBitmap(this.texture, 60, 100, false);
					canvas.drawBitmap(bitmap, this.matrix, new Paint());
				}
				break;

				case "line":
				{
					paint.setColor(Color.rgb(0, 0, 0));
					Log.d("draw", "Ox = " + this.old_point.x + ", Oy = " + this.old_point.y + ", x = " + this.point.x + ", y = " + this.point.y);
					Bitmap bitmap = Bitmap.createScaledBitmap(this.texture, 60, 100, false);
					canvas.drawBitmap(bitmap, this.matrix, new Paint());
					canvas.drawLine(this.start_line_x, this.start_line_y, this.end_line_x, this.end_line_y, paint);
//					for(Double d : this.points){
//						Log.d("list", "" + d);
//					}
//					int i = 0;
//					float pointsArray[] = new float[this.points.size()];
//					for(Double d : this.points){
//						if(d == -10000.0){
//							Log.d("draw", "書き出し");
//							for(float f : pointsArray){
//								Log.d("書き出し配列", "array = " + f);
//							}
//							canvas.drawLines(pointsArray, paint);
//							pointsArray = new float[this.points.size()];
//							i = 0;
//						}else{
//							pointsArray[i] = d.floatValue();
//							i++;
//						}
//					}
				}
				break;

				case "turn":
				{
					Log.d("draw", "TURN");
					Bitmap bitmap = Bitmap.createScaledBitmap(this.texture, 60, 100, false);
					canvas.drawBitmap(bitmap, this.matrix, new Paint());
				}
				break;
			}
			canvas.restore();
			//Log.d("point", "x = " + this.point.x + ", y = " + this.point.y);
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
			this.status = "move";
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
			this.status = "line";
			if(line_status.equals("run")){
				this.end_line_x = (float)this.point.x;
				this.end_line_y = (float)this.point.y;
			}
//			this.points.add(this.point.x);
//			this.points.add(this.point.y);
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
			this.matrix.postTranslate(-(float)this.point.x - (this.texture.getWidth() / 2.0f), -(float)this.point.y - (this.texture.getHeight() / 2.0f));
			this.matrix.postRotate(degree);
			this.status = "turn";
		}
	}

	public void startDrawLine(Point point){
		this.start_line_x = (float)this.point.x;
		this.start_line_y = (float)this.point.y;
		this.line_status = "run";
//		this.points.add(point.x);
//		this.points.add(point.y);
	}

	public void finishDrawLine(){
		this.line_status = "sleep";
//		this.points.add(-10000.0);
	}

	public void setTexture(int textureId) {
		this.texture = Manager.getTexture(textureId);
	}
}