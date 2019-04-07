package org.crazyit.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class DrawView extends View
{
	float preX;
	float preY;
	private Path path;
	public Paint paint = null;
	final int VIEW_WIDTH = 320;
	final int VIEW_HEIGHT = 480;
	// ����һ���ڴ��е�ͼƬ����ͼƬ����Ϊ������
	Bitmap cacheBitmap = null;
	// ����cacheBitmap�ϵ�Canvas����
	Canvas cacheCanvas = null;

	public DrawView(Context context, AttributeSet set)
	{
		super(context, set);
		// ����һ�����View��ͬ��С�Ļ�����
		cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH, VIEW_HEIGHT,
				Config.ARGB_8888);
		cacheCanvas = new Canvas();
		path = new Path();
		// ����cacheCanvas������Ƶ��ڴ��е�cacheBitmap��
		cacheCanvas.setBitmap(cacheBitmap);
		// ���û��ʵ���ɫ
		paint = new Paint(Paint.DITHER_FLAG);
		paint.setColor(Color.RED);
		// ���û��ʷ��
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(1);
		// �����
		paint.setAntiAlias(true);
		paint.setDither(true);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// ��ȡ�϶��¼��ķ���λ��
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				path.moveTo(x, y);
				preX = x;
				preY = y;
				break;
			case MotionEvent.ACTION_MOVE:
				path.quadTo(preX, preY, x, y);
				preX = x;
				preY = y;
				break;
			case MotionEvent.ACTION_UP:
				cacheCanvas.drawPath(path, paint); // ��
				path.reset();
				break;
		}
		invalidate();
		// ����true�����������Ѿ�������¼�
		return true;
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		Paint bmpPaint = new Paint();
		// ��cacheBitmap���Ƶ���View�����
		canvas.drawBitmap(cacheBitmap, 0, 0, bmpPaint); // ��
		// ����path����
		canvas.drawPath(path, paint);
	}
}
