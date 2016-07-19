package com.example.yujimomoi.a1daysummerintern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.example.yujimomoi.a1daysummerintern.classFile.FieldArea;
import com.example.yujimomoi.a1daysummerintern.classFile.LogPrint;
import com.example.yujimomoi.a1daysummerintern.classFile.Player;
import com.example.yujimomoi.a1daysummerintern.classFile.Point;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Manager extends Activity {
	private Player player;
	private ActionManager actionManager;
	private static ViewRendere viewRendere;
	private FieldArea fieldArea;

	public Manager(ViewRendere viewRendere) {
		LogPrint.getInstans().setDrawFlag(false); // ログを書くか書かないか
		LogPrint.getInstans().logWrite("create", "Manager", true);
		this.viewRendere = viewRendere;
		this.actionManager = null;
		this.player = null;
		this.fieldArea = null;
	}

	public void init() {
		LogPrint.getInstans().logWrite("init", "Manager", true);
		this.fieldArea = new FieldArea();
		this.fieldArea.init();
		this.viewRendere.setObject(this.fieldArea);

		this.player = new Player(this);
		this.player.init();
		this.player.setPoint(400, 400);

		Player hoge = new Player(this);
		hoge.init();

		this.viewRendere.setObject(this.player);
		//this.viewRendere.setObject(hoge);

		this.actionManager = new ActionManager();
		this.actionManager.init();
		// オブジェクトの動きの設定
		this.actionManager.setActionWrite();

		for(int i = 0;i < 360;i++) {
			if(300 <= i) this.player.setLineColor(Color.RED);
			else if(240 <= i) this.player.setLineColor(Color.GREEN);
			else if(180 <= i) this.player.setLineColor(Color.BLACK);
			else if(120 <= i) this.player.setLineColor(Color.YELLOW);
			else if(60 <= i) this.player.setLineColor(Color.GRAY);
			this.player.moveWithLine(2);
			this.player.turn(1);
		}
		this.player.move(400);

		// オブジェクトの動きの設定を終了
		this.actionManager.setActionWriteEnd();
		LogPrint.getInstans().logWrite("init_end", "Manager", true);
	}

	public void update() {
		this.actionManager.update();
		this.player.update();
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
}
