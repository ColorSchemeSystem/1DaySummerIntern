package com.example.yujimomoi.a1daysummerintern;

import android.app.Activity;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;

import com.example.yujimomoi.a1daysummerintern.classFile.Player;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Manager extends Activity {
	private Player player;
	private ActionManager actionManager;
	private static ViewRendere viewRendere;

	public Manager(ViewRendere viewRendere) {
		Log.d("create", "Manager");
		this.viewRendere = viewRendere;
		this.actionManager = null;
		this.player = null;
	}

	public void init() {
		Log.d("init", "Manager");
		this.actionManager = new ActionManager();
		this.player = new Player(this.actionManager);

		this.player.init();
		this.actionManager.init();

//        this.actionManager.setData("test1");
//        this.actionManager.setData("test2");
//        this.actionManager.setData("test3");
//
//        String action = this.actionManager.getDataOneLine();
//        while (action != null) {
//            Log.d("action", action);
//            action = this.actionManager.getDataOneLine();
//        }

		Player hoge = new Player(this.actionManager);
		hoge.init();
		hoge.point.x = 300;
		hoge.point.y = 300;

		this.viewRendere.setObject(this.player);
		this.viewRendere.setObject(hoge);
		Log.d("init_end", "Manager");

		// オブジェクトの動きの設定
		this.actionManager.setActionWrite();

		this.player.move(50);
		this.player.turn(90);
		this.player.move(50);

		// オブジェクトの動きの設定を終了
		this.actionManager.setActionWriteEnd();
	}

	public void update() {
		this.player.update();
	}

	public static Bitmap getTexture(int id) {
		Log.d("Manager", "getTexture");
		Bitmap texture = null;
		if(viewRendere != null) texture = viewRendere.getTexture(id);
		Log.d("Manager", "getTexture texture : " + String.valueOf(texture));
		return texture;
	}
}
