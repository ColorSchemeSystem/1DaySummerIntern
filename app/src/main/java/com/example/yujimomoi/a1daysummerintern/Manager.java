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

		Player hoge = new Player(this);
		hoge.init();

		this.viewRendere.setObject(this.player);
		this.viewRendere.setObject(hoge);

		this.actionManager = new ActionManager();
		this.actionManager.init();

		this.player.setPoint(400, 0);
		hoge.setPoint(400, 0);
		// オブジェクトの動きの設定
		this.actionManager.setActionWrite();

		this.player.setLineColor(Color.YELLOW);
		this.player.turn(180);
		this.player.turn(-18);
		this.player.moveWithLine(300);
		this.player.turn(-72);
		this.player.moveWithLine(300);
		this.player.turn(-234);
		this.player.moveWithLine(300);
		this.player.turn(-54);
		this.player.moveWithLine(300);
		this.player.turn(152);
		this.player.moveWithLine(300);

		this.player.move(600);

		hoge.setLineColor(Color.YELLOW);
		hoge.turn(180);
		hoge.turn(18);
		hoge.moveWithLine(300);
		hoge.turn(72);
		hoge.moveWithLine(300);
		hoge.turn(234);
		hoge.moveWithLine(300);
		hoge.turn(54);
		hoge.moveWithLine(300);
		hoge.turn(-152);
		hoge.moveWithLine(300);

		hoge.move(600);

		// オブジェクトの動きの設定を終了
		this.actionManager.setActionWriteEnd();
		LogPrint.getInstans().logWrite("init_end", "Manager", true);
	}

	public void update() {
		//LogPrint.getInstans().logWrite("update","Manager",true);
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
