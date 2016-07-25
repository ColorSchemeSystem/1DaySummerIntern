package com.example.yujimomoi.a1daysummerintern.classFile;

import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by yuji.momoi on 2016/07/08.
 */
public class BaseObject {
	public static final int OBJ_TYPE_PLAYER = 0;
	public static final int OBJ_TYPE_FIELD = 1;
	private static ArrayList<BaseObject> objList = new ArrayList<BaseObject>();
	public static int max_id = 0;
	public int id;
	private int obj_type;

	public BaseObject(int type) {
		id = max_id;
		max_id ++;
		this.obj_type = type;
	}

	protected void setObj(BaseObject obj) {
		objList.add(obj);
	}
	public static BaseObject getObj(int index) {return objList.get(index);}

	public void init(){}
	public void update(){}
	public void draw(Canvas canvas){}
	public int getType() {return obj_type;}
	public void remove() {
		objList.remove(this);
		max_id --;
	}
}
