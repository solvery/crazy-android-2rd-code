/**
 *
 */
package org.crazyit.event;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
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
public class PlaneView extends View
{
	public float currentX;
	public float currentY;
	Bitmap plane;
	// ���塢����������
	Paint p = new Paint();
	public PlaneView(Context context)
	{
		super(context);
		// ����ɻ�ͼƬ
		plane = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.plane);
		setFocusable(true);
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// ���Ʒɻ�
		canvas.drawBitmap(plane, currentX, currentY, p);
	}
}

