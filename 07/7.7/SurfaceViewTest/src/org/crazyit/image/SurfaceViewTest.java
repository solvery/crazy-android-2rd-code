package org.crazyit.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * Description: <br/>
 * site: <a href="http://www.crazyit.org">crazyit.org</a> <br/>
 * Copyright (C), 2001-2014, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class SurfaceViewTest extends Activity
{
	// SurfaceHolder����ά��SurfaceView�ϻ��Ƶ�����
	private SurfaceHolder holder;
	private Paint paint;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		paint = new Paint();
		SurfaceView surface = (SurfaceView) findViewById(R.id.show);
		// ��ʼ��SurfaceHolder����
		holder = surface.getHolder();
		holder.addCallback(new Callback()
		{
			@Override
			public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2,
					int arg3)
			{
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder)
			{
				// ��������SurfaceView
				Canvas canvas = holder.lockCanvas();
				// ���Ʊ���
				Bitmap back = BitmapFactory.decodeResource(
					SurfaceViewTest.this.getResources()
					, R.drawable.sun);
				// ���Ʊ���
				canvas.drawBitmap(back, 0, 0, null);
				// ������ɣ��ͷŻ������ύ�޸�
				holder.unlockCanvasAndPost(canvas);
				// ������һ�Σ�"�־û�"�ϴ������Ƶ�����
				holder.lockCanvas(new Rect(0, 0, 0, 0));
				holder.unlockCanvasAndPost(canvas);
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder)
			{
			}
		});
		// Ϊsurface�Ĵ����¼��󶨼�����
		surface.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View source, MotionEvent event)
			{
				// ֻ�������¼�
				if (event.getAction() == MotionEvent.ACTION_DOWN)
				{
					int cx = (int) event.getX();
					int cy = (int) event.getY();
					// ����SurfaceView�ľֲ�����ֻ���¾ֲ�����
					Canvas canvas = holder.lockCanvas(new Rect(cx - 50,
							cy - 50, cx + 50, cy + 50));
					// ����canvas�ĵ�ǰ״̬
					canvas.save();
					// ��ת����
					canvas.rotate(30, cx, cy);
					paint.setColor(Color.RED);
					// ���ƺ�ɫ����
					canvas.drawRect(cx - 40, cy - 40, cx, cy, paint);
					// �ָ�Canvas֮ǰ�ı���״̬
					canvas.restore();
					paint.setColor(Color.GREEN);
					// ������ɫ����
					canvas.drawRect(cx, cy, cx + 40, cy + 40, paint);
					// ������ɣ��ͷŻ������ύ�޸�
					holder.unlockCanvasAndPost(canvas);
				}
				return false;
			}
		});
	}
}