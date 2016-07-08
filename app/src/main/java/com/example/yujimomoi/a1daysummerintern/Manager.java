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

        Player hoge = new Player();
        hoge.init();
        hoge.point.x = 300;
        hoge.point.y = 300;

        this.viewRendere.setObject(this.player);
        this.viewRendere.setObject(hoge);
        Log.d("init_end", "Manager");
    };

    public void update() {
        this.player.update();
    };

    public static Bitmap getTexture(int id) {
        Log.d("Manager", "getTexture");
        Bitmap texture = null;
        if(viewRendere != null) texture = viewRendere.getTexture(id);
        Log.d("Manager", "getTexture texture : " + String.valueOf(texture));
        return texture;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        Log.d("key_Event", "in");
        if (e.getKeyCode() == KeyEvent.KEYCODE_W) {
           if(e.getAction() == KeyEvent.ACTION_DOWN) {
               this.player.point.y += 0.1f;
           }
        }
        return super.dispatchKeyEvent(e);
    }
}
