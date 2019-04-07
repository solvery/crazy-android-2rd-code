/**
 *
 */
package org.crazyit.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
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
public class MyView extends View
{
	// ��������
	public Paint paint;
	public MyView(Context context , AttributeSet set)
	{
		super(context , set);
		paint = new Paint();
		paint.setColor(Color.RED);
	}
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// ʹ��ָ��Paint���󻭾���
		canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
	}
}
