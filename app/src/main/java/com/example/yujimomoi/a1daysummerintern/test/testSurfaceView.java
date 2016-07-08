package com.example.yujimomoi.a1daysummerintern.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.yujimomoi.a1daysummerintern.R;

import java.util.Random;

/**
 * Created by yuji.momoi on 2016/07/08.
 */
public class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    Random random = new Random();
    Bitmap bitmap;  //表示画像
    float x;   //表示位置x
    float y;   //表示位置y
    int bitmapWidth; //画像サイズ 幅
    int bitmapHeight; //画像サイズ 高さ

    Thread thread = null; //画像を表示するスレッド

    float height;

    public TestSurfaceView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable._app_icon_dameo);     //①
        bitmapWidth = bitmap.getWidth();
        bitmapHeight = bitmap.getHeight();
        x = 0;
        y = 0;
        getHolder().addCallback(this);                               //②
    }

    //画像を表示するスレッド
    private class DrawThread extends Thread {
        public void run() {
            SurfaceHolder holder = getHolder();
            while(true) {
                Canvas canvas = holder.lockCanvas();                 //③
                if (canvas != null) {
                    mydraw(canvas);
                    holder.unlockCanvasAndPost(canvas);              //④
                }
            }
        }
    }
    //画像を表示する
    private void mydraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        int width  = canvas.getWidth() - bitmapWidth;
        height = canvas.getHeight() - bitmapHeight;

        //x = random.nextInt(width);
        //y = random.nextInt(height);

        canvas.drawBitmap(bitmap, x, y, new Paint());
    }
    //-----画面が生成された時に呼び出される            ⑤
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new DrawThread();
        thread.start();
    }
    //-----画面のサイズ等が変化した時に呼び出される    ⑥
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
    //-----画面が削除された時に呼び出される            ⑦
    public void surfaceDestroyed(SurfaceHolder holder) {
        thread = null;
    }

    public void update() {
        if(height > this.y) {
            this.y += 0.001f;
        } else {
            //Log.d("info", String.valueOf(height));
        }
    }
}
