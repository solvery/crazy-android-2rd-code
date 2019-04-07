/**
 *
 */
package org.crazyit.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
	public float currentX = 40;
	public float currentY = 50;
	// ���塢����������
	Paint p = new Paint();
	public DrawView(Context context)
	{
		super(context);
	}	
	public DrawView(Context context , AttributeSet set)
	{
		super(context ,set);
	}
	@Override
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// ���û��ʵ���ɫ
		p.setColor(Color.RED);
		// ����һ��СԲ����ΪС��
		canvas.drawCircle(currentX, currentY, 15, p);
	}

	// Ϊ������Ĵ����¼���д�¼�������
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		// �޸�currentX��currentY��������
		currentX = event.getX();
		currentY = event.getY();
		// ֪ͨ��ǰ����ػ��Լ�
		invalidate();
		// ����true�����ô������Ѿ�������¼�
		return true;
	}
}
