package com.example.yujimomoi.a1daysummerintern;

import android.util.Log;

import com.example.yujimomoi.a1daysummerintern.classFile.BaseObject;

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
	private static String line_status;

	public ActionManager() {
		Log.d("create", "ActionManager");
		this.data = null;
		this.actionAmount = 0;
		this.line = 0;
		writeAction = false;
		this.line_status = "sleep";
	}

	public void init() {
		Log.d("init", "ActionManager");
		if(this.data != null) this.data.clear();
		this.data = new HashMap<String, String>();
		writeAction = false;
		Log.d("init_end", "ActionManager");
	}

	public void update() {
		String action = getDataOneLine();
		if(action == null) return;
		String act[] = action.split(" ");

		int id = Integer.parseInt(act[0]);
		if(id < BaseObject.max_id) {
			switch (act[1]) {
				case "move": {
					int amount = Integer.parseInt(act[2]);
					boolean line = Boolean.parseBoolean(act[3]);
					if (!line) {
						if (this.line_status.equals("run")){
							BaseObject.finishDrawLine(id);
							this.line_status = "sleep";
						}
						BaseObject.move(id, amount);
					} else {
						if (this.line_status.equals("sleep")){
							BaseObject.startDrawLine(id);
							this.line_status = "run";
						}
						BaseObject.moveWithLine(id, amount);
					}
				}
				break;
				case "turn":
				{
					if (this.line_status.equals("run")){
						BaseObject.finishDrawLine(id);
						this.line_status = "sleep";
					}
					int degree = Integer.parseInt(act[2]);
					BaseObject.turn(id, degree);
				}
				break;
			}
		}
	}

	public void setData(String value) {
		//Log.d("setDate", value);
		this.data.put(String.valueOf(this.actionAmount), value);
		this.actionAmount ++;
	}

	public String getDataOneLine() {
		String ans = null;
		if(this.actionAmount > this.line) {
			ans = this.data.get(String.valueOf(this.line));
			this.line ++;
		}
		//if(ans != null) Log.d("getDate", String.valueOf(ans));
		return ans;
	}

	public void setActionWrite() {writeAction = true;}

	public void setActionWriteEnd() {writeAction = false;}

	public static boolean getWriteAction() {return writeAction;}
}
