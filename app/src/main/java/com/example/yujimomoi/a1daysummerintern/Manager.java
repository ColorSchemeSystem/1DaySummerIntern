package com.example.yujimomoi.a1daysummerintern;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.yujimomoi.a1daysummerintern.classFile.Player;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

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

		this.player.moveWithLine(200);
		this.player.turn(90);
		this.player.moveWithLine(200);
		this.player.turn(90);
		this.player.moveWithLine(200);
		this.player.turn(90);
		this.player.moveWithLine(200);


		// オブジェクトの動きの設定を終了
		this.actionManager.setActionWriteEnd();
		Log.d("init_end", "Manager");
	}

	public void update() {
		this.actionManager.update();
		this.player.update();
	}

	public static Bitmap getTexture(int id) {
		Log.d("Manager", "getTexture");
		Bitmap texture = null;
		if(viewRendere != null) texture = viewRendere.getTexture(id);
		Log.d("Manager", "getTexture texture : " + String.valueOf(texture));
		return texture;
	}

	public void setData(String data) {
		this.actionManager.setData(data);
	}
}
