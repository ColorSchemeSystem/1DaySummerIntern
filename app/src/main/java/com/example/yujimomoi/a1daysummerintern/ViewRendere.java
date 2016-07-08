package com.example.yujimomoi.a1daysummerintern;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.yujimomoi.a1daysummerintern.classFile.BaseObject;


/**
 * Created by yuji.momoi on 2016/07/08.
 */
public class ViewRendere extends SurfaceView implements SurfaceHolder.Callback{
    private Thread thread; //描画スレッド
    private BaseObject obj[];
    private Context cont;

    public ViewRendere(Context context) {
        super(context);
        this.thread = null;
        this.obj = null;
        this.cont = context;
        getHolder().addCallback(this);
    };

    // 描画用スレッド
    private class DrawThread extends Thread {
        public void run() {
            SurfaceHolder holder = getHolder();
            while(true) {
                Canvas canvas = holder.lockCanvas();
                if (canvas != null) {
                    mydraw(canvas);
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    };

    // 描画
    private void mydraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        if(this.obj != null) {
            for (BaseObject o : this.obj) {
                o.draw(canvas);
            }
        }
    };

    public void surfaceCreated(SurfaceHolder holder) {
        thread = new DrawThread();
        thread.start();
    };

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    };

    public void surfaceDestroyed(SurfaceHolder holder) {
        thread = null;
    };

    public void setObject(BaseObject object) {
        this.obj[this.obj.length - 1] = object;
    };
}
