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

	// ここの中に書いてね
	public void set() {

		// 線を描くためのオブジェクトを生成
		Player player = new Player(this);
		Player player2 = new Player(this);

		// プレイヤーの表示位置(初期座標)の設定
		player.setPoint(400, 400);
		player2.setPoint(400, 400);

		// 三角を描く方の車を青い車に変更
		player2.setTexture(BLUE_CAR);

		// 画面上に表示するために上記で生成したオブジェクトをセットする
		this.viewRendere.setObject(player);
		this.viewRendere.setObject(player2);

		// オブジェクトの動きを設定するためにActionManagerを生成
		actionManager = new ActionManager();

		// オブジェクトの動きの設定
		this.actionManager.setActionWrite();

		player.setLineColor(Color.RED);
		this.drawCircle(player, 200);
		player.setLineColor(Color.GREEN);
		this.drawCircle(player, 180);
		player.setLineColor(Color.BLUE);
		this.drawCircle(player, 160);
		player.setLineColor(Color.BLACK);
		this.drawCircle(player, 140);
		player.setLineColor(Color.RED);
		this.drawCircle(player, 120);

		player2.setLineColor(Color.GREEN);
		this.drawCircle(player2, 100);
		player2.setLineColor(Color.BLUE);
		this.drawCircle(player2, 80);
		player2.setLineColor(Color.BLACK);
		this.drawCircle(player2, 60);
		player2.setLineColor(Color.RED);
		this.drawCircle(player2, 40);
		player2.setLineColor(Color.BLUE);
		this.drawCircle(player2, 20);

		// オブジェクトの動きの設定を終了
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
