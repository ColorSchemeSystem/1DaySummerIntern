package com.example.yujimomoi.a1daysummerintern;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuji.momoi on 2016/07/08.
 */
public class ActionManager {
	private Map<String, String> data;
	private int actionAmount;
	private int line;
	private static boolean writeAction = false;

	public ActionManager() {
		Log.d("create", "ActionManager");
		this.data = null;
		this.actionAmount = 0;
		this.line = 0;
		writeAction = false;
	}

	public void init() {
		Log.d("init", "ActionManager");
		if(this.data != null) this.data.clear();
		this.data = new HashMap<String, String>();
		writeAction = false;
		Log.d("init_end", "ActionManager");
	}

	public void setData(String value) {
		Log.d("setDate", value);
		this.data.put(String.valueOf(this.actionAmount), value);
		this.actionAmount ++;
	}

	public String getDataOneLine() {
		String ans = null;
		if(this.actionAmount > this.line) {
			ans = this.data.get(String.valueOf(this.line));
			this.line ++;
		}
		Log.d("getDate", String.valueOf(ans));
		return ans;
	}

	public void setActionWrite() {writeAction = true;}

	public void setActionWriteEnd() {writeAction = false;}

	public static boolean getWriteAction() {return writeAction;}
}
