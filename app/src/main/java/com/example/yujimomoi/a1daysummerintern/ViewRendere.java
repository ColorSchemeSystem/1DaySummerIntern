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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.yujimomoi.a1daysummerintern.classFile.BaseObject;
import com.example.yujimomoi.a1daysummerintern.classFile.LogPrint;
import com.example.yujimomoi.a1daysummerintern.classFile.Point;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by yuji.momoi on 2016/07/08.
 */
public class ViewRendere extends View {
	private ArrayList<BaseObject> obj;
	private int texture_name_list[] = {R.drawable.car_sample001};
	private Bitmap texture_list[];
	private static final long INTERVAL_PERIOD = 1;
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
		this.obj = new ArrayList<BaseObject>();

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
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		// fps（実測値）の計測
		intervalTime.add(System.currentTimeMillis());
		float fps = 20000 / (intervalTime.get(19) - intervalTime.get(3));
		intervalTime.remove(0);

		if(this.obj != null) {
			for (BaseObject o : this.obj) {
				canvas.save();
				o.draw(canvas);
				canvas.restore();
			}
		}
		canvas.drawText(String.format("%.0f fps", fps), 0, FONT_SIZE, paintFps);

		invalidate();
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

	public void setObject(BaseObject object) {
		Log.d("ViewRendere", "setObject");

		obj.add(object);
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
