package com.example.yujimomoi.a1daysummerintern;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.yujimomoi.a1daysummerintern.classFile.BaseObject;
import com.example.yujimomoi.a1daysummerintern.classFile.FieldArea;
import com.example.yujimomoi.a1daysummerintern.classFile.LogPrint;
import com.example.yujimomoi.a1daysummerintern.classFile.Player;
import com.example.yujimomoi.a1daysummerintern.classFile.Point;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Manager extends Activity {
	private ActionManager actionManager;
	private static ViewRendere viewRendere;
	private FieldArea fieldArea;
	private static Boolean end_flag = false;

	private final int RED_CAR = R.drawable.car_sample001;
	private final int BLUE_CAR = R.drawable.car_sample002;
	private final int GREEN_CAR = R.drawable.car_sample003;
	private final int BLACK_CAR = R.drawable.car_sample004;
	private final int YELLOW_CAR = R.drawable.car_sample005;

	// ここの中に書いてね
	public void set() {

		// アンパンマン
//		// 線を描くためのオブジェクトを生成
//		Player p = new Player(this);
//		Player p2 = new Player(this);
//		Player p3 = new Player(this);
//		Player p4 = new Player(this);
//		Player p5 = new Player(this);
//
//		// プレイヤーの表示位置(初期座標)の設定
//		p.setPoint(500, 100);
//		p2.setPoint(200, 100);
//		p3.setPoint(800, 100);
//		p4.setPoint(250, -100);
//		p5.setPoint(750, -100);
//
//		// playerの色設定
//		p.setTexture(RED_CAR);
//		p2.setTexture(GREEN_CAR);
//		p3.setTexture(BLUE_CAR);
//		p4.setTexture(YELLOW_CAR);
//		p5.setTexture(BLACK_CAR);
//
//		// 画面上に表示するために上記で生成したオブジェクトをセットする
//		this.viewRendere.setObject(p);
//		this.viewRendere.setObject(p2);
//		this.viewRendere.setObject(p3);
//		this.viewRendere.setObject(p4);
//		this.viewRendere.setObject(p5);
//
//		// オブジェクトの動きを設定するためにActionManagerを生成
//		actionManager = new ActionManager();
//
//		p.turn(180);
//		p2.turn(180);
//		p3.turn(180);
//		p4.turn(180);
//		p5.turn(180);
//
//		// オブジェクトの動きの設定
//		this.actionManager.setActionWrite();
//
//		p.move(400);
//		drawCircle(p, 450);
//		p.setLineColor(Color.RED);
//		drawCircle(p, 150);
//
//		p2.setLineColor(Color.RED);
//		p2.move(400);
//		drawCircle(p2, 150);
//		p2.turn(-30);
//		p2.move(150);
//
//		p3.setLineColor(Color.RED);
//		p3.move(400);
//		drawCircle(p3, 150);
//		p3.turn(30);
//		p3.move(150);
//
//		p2.setLineColor(Color.BLACK);
//		p3.setLineColor(Color.BLACK);
//		for(int i = 0;i < 60;i++) {
//			p2.turn(-1);
//			p2.moveWithLine(300 / 60.f);
//
//			p3.turn(1);
//			p3.moveWithLine(300 / 60.f);
//		}
//
//		p4.move(400);
//		p4.turn(180);
//
//		p5.move(400);
//		p5.turn(180);
//
//		for (int i = 0;i < 180;i++) {
//			p4.turn(1);
//			p4.moveWithLine(300 / 180.f);
//
//			p5.turn(-1);
//			p5.moveWithLine(300 / 180.f);
//		}
//
//		// オブジェクトの動きの設定を終了
//		this.actionManager.setActionWriteEnd();


		Player p = new Player(this);

		// playerの色設定
		p.setTexture(RED_CAR);

		p.setPoint(200,800);

		// 画面上に表示するために上記で生成したオブジェクトをセットする
		this.viewRendere.setObject(p);

		this.actionManager = new ActionManager();

		//p.turn(90);

		this.actionManager.setActionWrite();
		p.setDirection(0);
		drawPolyhedron(p, 3, 300);
		p.setDirection(0);
		p.move(400);
		drawPolyhedron(p, 4, 300);
		p.setDirection(0);
		p.move(600);
		drawPolyhedron(p, 5, 300);
		this.actionManager.setActionWriteEnd();
	}

	public Manager(ViewRendere viewRendere) {
		LogPrint.getInstans().setDrawFlag(false); // ログを書くか書かないか
		LogPrint.getInstans().logWrite("create", "Manager", true);
		this.viewRendere = viewRendere;
		this.actionManager = null;
		this.fieldArea = null;
	}

	public void init() {
		LogPrint.getInstans().logWrite("init", "Manager", true);
		this.fieldArea = new FieldArea();
		this.fieldArea.init();
		this.viewRendere.setObject(this.fieldArea);

		this.set();

		LogPrint.getInstans().logWrite("init_end", "Manager", true);
	}

	public void update() {
		if(!end_flag) this.actionManager.update();
		else remove();
	}

	public static Bitmap getTexture(int id) {
		LogPrint.getInstans().logWrite("Manager", "getTexture");
		Bitmap texture = null;
		if(viewRendere != null) texture = viewRendere.getTexture(id);
		LogPrint.getInstans().logWrite("Manager", "getTexture texture : " + String.valueOf(texture));
		return texture;
	}

	public void setData(String data) {
		this.actionManager.setData(data);
	}

	public void setLineData(Paint color, Point startPoint, Point endPoint) {
		this.fieldArea.setLineData(color, startPoint, endPoint);
	}

	public void drawPolyhedron(Player target, int vartex, float radius) {
		for(int i = 0; i < vartex; i++) {
			target.turn((float) (360 / vartex));
			target.moveWithLine(radius);
		}
	}

	public void drawStar(Player target, float radius) {
		int vartex = 5;
		float rotation = (360 - (float) (360 / vartex)) / 2.0f;
		for (int i = 0; i < vartex; i++) {
			target.turn(360 - rotation);
			target.moveWithLine(radius);
		}
	}

	public void drawCircle(Player target, float radius) {
		target.move(radius);
		target.turn(90);
		float amount = (radius * 2) * (float) Math.PI;
		for(int i = 0;i < 360;i++) {
			target.turn(1);
			target.moveWithLine(amount / 360);
		}
		target.turn(90);
		target.move(radius);
		target.turn(180);
	}

	public void remove() {
		for (int i = 0;i < BaseObject.max_id;i++) {
			BaseObject object = BaseObject.getObj(i);
			int type = object.getType();
			if(type != BaseObject.OBJ_TYPE_FIELD) {
				this.viewRendere.removeObject(object);
				object.remove();
			}
		}
	}

	public static void setEnd() {
		end_flag = true;
	}
}
