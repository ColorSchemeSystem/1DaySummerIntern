package com.example.yujimomoi.a1daysummerintern.classFile;

import java.util.ArrayList;

/**
 * Created by yuji.momoi on 2016/07/15.
 */
public class StringData {
	private ArrayList<String> data = new ArrayList<String>();

	public StringData() {}

	public void setData(String data) {
		this.data.add(data);
	}

	public String getData(int index) {
		if(index < 0 || index >= this.data.size()) return String.valueOf(null);
		return this.data.get(index);
	}
}
