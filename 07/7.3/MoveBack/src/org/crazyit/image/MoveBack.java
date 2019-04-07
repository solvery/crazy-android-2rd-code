package org.crazyit.image;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

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
public class MoveBack extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}

	class MyView extends View
	{
		// ��¼����λͼ��ʵ�ʸ߶�
		final int BACK_HEIGHT = 1700;
		// ����ͼƬ
		private Bitmap back;
		private Bitmap plane;
		// ����ͼƬ�Ŀ�ʼλ��
		final int WIDTH = 320;
		final int HEIGHT = 440;
		private int startY = BACK_HEIGHT - HEIGHT;

		public MyView(Context context)
		{
			super(context);
			back = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.back_img);
			plane = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.plane);
			final Handler handler = new Handler()
			{
				public void handleMessage(Message msg)
				{
					if (msg.what == 0x123)
					{
						// ���¿�ʼ�ƶ�
						if (startY <= 0)
						{
							startY = BACK_HEIGHT - HEIGHT;
						}
						else
						{
							startY -= 3;
						}
					}
					invalidate();
				}
			};
			new Timer().schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					handler.sendEmptyMessage(0x123);
				}

			}, 0, 100);
		}

		@Override
		public void onDraw(Canvas canvas)
		{
			// ����ԭʼλͼ��Matrix������ͼƬ
			Bitmap bitmap2 = Bitmap
					.createBitmap(back, 0, startY, WIDTH, HEIGHT); // ��
			// ������λͼ
			canvas.drawBitmap(bitmap2, 0, 0, null);
			// ���Ʒɻ�
			canvas.drawBitmap(plane, 160, 360, null);
		}
	}
}