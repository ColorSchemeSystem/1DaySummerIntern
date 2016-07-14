package com.example.yujimomoi.a1daysummerintern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.yujimomoi.a1daysummerintern.classFile.BaseObject;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by yuji.momoi on 2016/07/08.
 */
public class ViewRendere extends SurfaceView implements SurfaceHolder.Callback{
	private Thread thread; //描画スレッド
	private BaseObject obj[];
	private Context cont;
	private int texture_name_list[] = {R.drawable._app_icon_dameo};
	private Bitmap texture_list[];
	private ScheduledExecutorService scheduledExecutorService;
	private ArrayList<Long> intervalTime = new ArrayList<Long>(20);

	public ViewRendere(Context context) {
		super(context);
		this.texture_list = new Bitmap[texture_name_list.length];
		for(int i = 0;i < texture_name_list.length;i++) {
			this.texture_list[i] = BitmapFactory.decodeResource(getResources(), texture_name_list[i]);
		}
		this.thread = null;
		this.obj = null;
		this.cont = context;
		getHolder().addCallback(this);

		for(int i = 0;i < 20;i++) {
			intervalTime.add(System.currentTimeMillis());
		}
	}

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
	}

	// 描画
	private void mydraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);

		if(this.obj != null) {
			for (BaseObject o : this.obj) {
				canvas.save();
				o.draw(canvas);
				canvas.restore();
			}
		}
	}

	public Bitmap getTexture(int id) {
		Log.d("ViewRendere", "getTexture");
		for(int i = 0;i < this.texture_name_list.length;i++) {
			if(this.texture_name_list[i] == id) {
				return this.texture_list[i];
			}
		}
		return null;
	}

	public void surfaceCreated(final SurfaceHolder holder) {
		thread = new DrawThread();
		thread.start();

//		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//			@Override
//			public void run() {
//				intervalTime.add(System.currentTimeMillis());
//				float fps = 20000 / (intervalTime.get(19) - intervalTime.get(0));
//
//				Canvas canvas = holder.lockCanvas();
//				//mydraw(canvas);
//				Paint paint = new Paint();
//				canvas.drawText(String.format("%.1f fps", fps), 0 ,24f, new Paint());
//				holder.unlockCanvasAndPost(canvas);
//			}
//		}, 60 / 1000, 16, TimeUnit.MILLISECONDS);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		thread = null;
	}

	public void setObject(BaseObject object) {
		Log.d("ViewRendere", "setObject");

		int length = 1;
		if(this.obj != null) length = this.obj.length + 1;
		BaseObject array[] = new BaseObject[length];
		for(int i = 0;i < array.length - 1;i++) {
			array[i] = this.obj[i];
		}
		array[array.length - 1] = object;
		Log.d("ViewRendere", "set : " + String.valueOf(length));
		this.obj = array;
	}
}
