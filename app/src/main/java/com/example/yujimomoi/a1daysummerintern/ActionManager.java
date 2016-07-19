package com.example.yujimomoi.a1daysummerintern;

import android.util.Log;

import com.example.yujimomoi.a1daysummerintern.classFile.BaseObject;
import com.example.yujimomoi.a1daysummerintern.classFile.StringData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuji.momoi on 2016/07/08.
 */
public class ActionManager {
	private Map<String, String> save_buff;
	private int actionAmount;
	private int line;
	private static boolean writeAction = false;
	private static String line_status;
	private StringData data_buff[];
	private int data_buff_amount[];
	private int data_buff_cnt[];
	private Boolean action_flag;

	public ActionManager() {
		Log.d("create", "ActionManager");
		this.save_buff = null;
		this.actionAmount = 0;
		this.line = 0;
		this.action_flag = false;
		writeAction = false;
		this.line_status = "sleep";
	}

	public void init() {
		Log.d("init", "ActionManager");
		if(this.save_buff != null) this.save_buff.clear();
		this.save_buff = new HashMap<String, String>();
		writeAction = false;
		this.data_buff = new StringData[BaseObject.max_id];
		this.data_buff_amount = new int[BaseObject.max_id];
		this.data_buff_cnt = new int[BaseObject.max_id];
		for(int i = 0;i < BaseObject.max_id;i++) {
			Log.d("data_buff","number : " + i);
			this.data_buff[i] = new StringData();
			Log.d("data_buff","number : " + i + " sucssece");
			this.data_buff_amount[i] = 0;
			this.data_buff_cnt[i] = 0;
		}
		Log.d("init_end", "ActionManager");
	}

	public void update() {
		if(!action_flag) return;
		for(int i = 0;i < BaseObject.max_id;i++) {
			if(data_buff_cnt[i] < data_buff_amount[i]) {
				String data = data_buff[i].getData(data_buff_cnt[i]);
				data_buff_cnt[i] ++;
				String action[] = data.split(" ");
				switch (action[1]) {
					case "move":
					{
						int amount = Integer.parseInt(action[2]);
						boolean line = Boolean.parseBoolean(action[3]);
						if(!line) BaseObject.move(i, amount);
						else BaseObject.moveWithLine(i, amount);
					}
					break;
					case "turn":
					{
						int degree = Integer.parseInt(action[2]);
						BaseObject.turn(i, degree);
					}
				}
			}
		}
	}

	public void setData(String value) {
		//Log.d("setDate", value);
		this.save_buff.put(String.valueOf(this.actionAmount), value);
		this.actionAmount ++;
	}

	public String getDataOneLine() {
		String ans = null;
		if(this.actionAmount > this.line) {
			ans = this.save_buff.get(String.valueOf(this.line));
			this.line ++;
		}
		//if(ans != null) Log.d("getDate", String.valueOf(ans));
		return ans;
	}

	public void setActionWrite() {writeAction = true;}

	public void setActionWriteEnd() {
		this.action_flag = true;
		String data = getDataOneLine();
		while(data != null) {
			String act[] = data.split(" ");

			int id = Integer.parseInt(act[0]);
			if (id < BaseObject.max_id) {
				this.data_buff[id].setData(data);
				this.data_buff_amount[id]++;
			}
			data = getDataOneLine();
		}
		writeAction = false;
	}

	public static boolean getWriteAction() {return writeAction;}
}
