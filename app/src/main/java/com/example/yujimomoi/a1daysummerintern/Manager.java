package com.example.yujimomoi.a1daysummerintern;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;

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

	// 使える車の種類
	private final int RED_CAR = R.drawable.car_sample001;
	private final int BLUE_CAR = R.drawable.car_sample002;
	private final int GREEN_CAR = R.drawable.car_sample003;
	private final int BLACK_CAR = R.drawable.car_sample004;
	private final int YELLOW_CAR = R.drawable.car_sample005;

	// ここの中に書いてね
	public void set() {

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
