package org.crazyit.desktop;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;


/**
 * Description:
 * <br/>site: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class LiveWallpaper extends WallpaperService
{
	// ��¼�û��������λͼ
	private Bitmap heart;
	// ʵ��WallpaperService����ʵ�ֵĳ��󷽷�
	@Override
	public Engine onCreateEngine()
	{
		// ��������ͼƬ
		heart = BitmapFactory.decodeResource(getResources()
			, R.drawable.heart);
		// �����Զ����Engine
		return new MyEngine();
	}

	class MyEngine extends Engine
	{
		// ��¼��������Ƿ�ɼ�
		private boolean mVisible;
		// ��¼��ǰ��ǰ�û������¼��ķ���λ��
		private float mTouchX = -1;
		private float mTouchY = -1;
		// ��¼��ǰ��Ҫ���Ƶľ��ε�����
		private int count = 1;
		// ��¼���Ƶ�һ��������������任��X��Y�����ƫ��
		private int originX = 50 , originY = 50;
		// ���廭��
		private Paint mPaint = new Paint();
		// ����һ��Handler
		Handler mHandler = new Handler();
		// ����һ��������ִ�е�����
		private final Runnable drawTarget = new Runnable()
		{
			public void run()
			{
				drawFrame();
			}
		};

		@Override
		public void onCreate(SurfaceHolder surfaceHolder)
		{
			super.onCreate(surfaceHolder);
			// ��ʼ������
			mPaint.setARGB(76 , 0, 0 , 255);
			mPaint.setAntiAlias(true);
			mPaint.setStyle(Paint.Style.FILL);
			// ���ô������¼�
			setTouchEventsEnabled(true);
		}

		@Override
		public void onDestroy()
		{
			super.onDestroy();
			// ɾ���ص�
			mHandler.removeCallbacks(drawTarget);
		}

		@Override
		public void onVisibilityChanged(boolean visible)
		{
			mVisible = visible;
			// ������ɼ�ʱ��ִ��drawFrame()������
			if (visible)
			{
				// ��̬�ػ���ͼ��
				drawFrame();
			}
			else
			{
				// ������治�ɼ���ɾ���ص�
				mHandler.removeCallbacks(drawTarget);
			}
		}

		@Override
		public void onOffsetsChanged(float xOffset, float yOffset, float xStep,
			float yStep, int xPixels, int yPixels)
		{
			drawFrame();
		}

		@Override
		public void onTouchEvent(MotionEvent event)
		{
			// �����⵽��������
			if (event.getAction() == MotionEvent.ACTION_MOVE)
			{
				mTouchX = event.getX();
				mTouchY = event.getY();
			}
			else
			{
				mTouchX = -1;
				mTouchY = -1;
			}
			super.onTouchEvent(event);
		}

		// �������ͼ�εĹ��߷���
		private void drawFrame()
		{
			// ��ȡ�ñ�ֽ��SurfaceHolder
			final SurfaceHolder holder = getSurfaceHolder();
			Canvas c = null;
			try
			{
				// �Ի�������
				c = holder.lockCanvas();
				if (c != null)
				{
					// ���Ʊ���ɫ
					c.drawColor(0xffffffff);
					// �ڴ������������
					drawTouchPoint(c);
					// ���û��ʵ�͸����
					mPaint.setAlpha(76);
					c.translate(originX, originY);
					// ����ѭ������count������					
					for (int i = 0; i < count; i++)  //��
					{
						c.translate(80, 0);
						c.scale(0.95f, 0.95f);
						c.rotate(20f);
						c.drawRect(0, 0, 150, 75, mPaint);
					}
				}
			}
			finally
			{
				if (c != null) holder.unlockCanvasAndPost(c);
			}
			mHandler.removeCallbacks(drawTarget);
			// ������һ���ػ�
			if (mVisible)
			{
				count ++;
				if(count >= 50)
				{
					Random rand = new Random();
					count = 1;
					originX += (rand.nextInt(60) - 30); 
					originY += (rand.nextInt(60) - 30); 
					try
					{
						Thread.sleep(500);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				// ָ��0.1�������ִ��mDrawCubeһ��
				mHandler.postDelayed(drawTarget, 100);  //��
			}
		}

		// ����Ļ���������ԲȦ
		private void drawTouchPoint(Canvas c)
		{
			if (mTouchX >= 0 && mTouchY >= 0)
			{
				// ���û��ʵ�͸����
				mPaint.setAlpha(255);
				c.drawBitmap(heart , mTouchX, mTouchY, mPaint);
			}
		}
	}
}