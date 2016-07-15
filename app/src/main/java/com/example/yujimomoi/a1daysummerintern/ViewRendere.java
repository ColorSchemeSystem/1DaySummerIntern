package com.example.yujimomoi.a1daysummerintern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
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
	private BaseObject obj[];
	private int texture_name_list[] = {R.drawable.car_sample001};
	private Bitmap texture_list[];
	private static final long INTERVAL_PERIOD = 16;
	private ScheduledExecutorService scheduledExecutorService;
	private static final float FONT_SIZE = 48f;
	private Paint paintFps;
	private ArrayList<Long> intervalTime = new ArrayList<Long>(20);
	private Manager manager = null;

	public ViewRendere(Context context) {
		super(context);
		init();
	}

	public ViewRendere(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ViewRendere(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public void init () {
		this.texture_list = new Bitmap[texture_name_list.length];
		for(int i = 0;i < texture_name_list.length;i++) {
			this.texture_list[i] = BitmapFactory.decodeResource(getResources(), texture_name_list[i]);
		}
		this.obj = null;
		SurfaceHolder surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);

		// fps 計測用の設定値の初期化
		for (int i = 0; i < 19; i++) {
			intervalTime.add(System.currentTimeMillis());
		}

		// 描画に関する各種設定
		paintFps = new Paint();
		paintFps.setTypeface(Typeface.DEFAULT);
		paintFps.setTextSize(FONT_SIZE);
		paintFps.setColor(Color.BLACK);
		paintFps.setAntiAlias(true);
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

	public void surfaceCreated(final SurfaceHolder surfaceHolder) {
		// SingleThreadScheduledExecutor による単一 Thread のインターバル実行
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				// fps（実測値）の計測
				intervalTime.add(System.currentTimeMillis());
				float fps = 20000 / (intervalTime.get(19) - intervalTime.get(3));
				intervalTime.remove(0);

				// ロックした Canvas の取得
				Canvas canvas = surfaceHolder.lockCanvas();
				if(manager != null) manager.update();
				mydraw(canvas);
				canvas.drawText(String.format("%.0f fps", fps), 0, FONT_SIZE, paintFps);
				// ロックした Canvas の解放
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}, 100, INTERVAL_PERIOD, TimeUnit.MILLISECONDS);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
		scheduledExecutorService.shutdown();
		surfaceHolder.removeCallback(this);
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

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
