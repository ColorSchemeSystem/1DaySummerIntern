package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by yuji.momoi on 2016/07/08.
 */
public class BaseObject {
	protected static final int OBJ_TYPE_PLAYER = 0;
	private static ArrayList<BaseObject> objList = new ArrayList<BaseObject>();
	public static int max_id = 0;
	protected int id;
	private int obj_type;

	public BaseObject(int type) {
		id = max_id;
		max_id ++;
		this.obj_type = type;
	}

	protected void setObj(BaseObject obj) {
		objList.add(obj);
	}

	public void init(){}
	public void update(){}
	public void draw(Canvas canvas){}

	public static void move(int targetId, int amountOfMove) {
		Log.d("BaseObject","move  id = " + String.valueOf(targetId));
		if(targetId >= max_id) {
			Log.d("BaseObject","return id");
			return;
		}
		BaseObject target = null;
		for (BaseObject o : objList) {
			if(o.id == targetId) {
				target = o;
				break;
			}
		}
		if(target == null) {
			Log.d("BaseObject","return target");
			return;
		}
		Log.d("BaseObject","target get sucssece");
		switch (target.obj_type) {
			case OBJ_TYPE_PLAYER:
			{
				Player player = (Player) target;
				player.move(amountOfMove);
			}
			break;
		}
	}
	public static void moveWithLine(int targetId, int amountOfMove) {
		if(targetId >= max_id) return;
		BaseObject target = objList.get(targetId);
		switch (target.obj_type) {
			case OBJ_TYPE_PLAYER:
			{
				Player player = (Player) target;
				player.moveWithLine(amountOfMove);
			}
			break;
		}
	}
	public static void turn(int targetId, int degree) {
		if(targetId >= max_id) return;
		BaseObject target = objList.get(targetId);
		switch (target.obj_type) {
			case OBJ_TYPE_PLAYER:
			{
				Player player = (Player) target;
				player.turn(degree);
			}
			break;
		}
	}
}
