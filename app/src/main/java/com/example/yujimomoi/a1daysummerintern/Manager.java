package com.example.yujimomoi.a1daysummerintern;

import android.app.Activity;
import android.util.Log;

import com.example.yujimomoi.a1daysummerintern.classFile.Player;

/**
 * Created by yuji.momoi on 2016/07/07.
 */
public class Manager extends Activity{
    private Player player;
    private ActionManager actionManager;
    private ViewRendere viewRendere;

    public Manager(ViewRendere viewRendere) {
        Log.d("create", "Manager");
        this.viewRendere = viewRendere;
        this.actionManager = null;
        this.player = null;
    };

    public void init() {
        Log.d("init", "Manager");
        this.player = new Player();
        this.actionManager = new ActionManager();

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
    };

    public void update() {
        this.player.update();
    };
}
