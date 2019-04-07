/**
 *
 */
package org.crazyit.sensor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
	// ����ˮƽ���Ǳ���ͼƬ
	Bitmap back;
	// ����ˮƽ���е�����ͼ��
	Bitmap bubble;
	// ����ˮƽ�������� ��X��Y����
	int bubbleX, bubbleY;

	public MyView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// ����ˮƽ��ͼƬ������ͼƬ
		back = BitmapFactory.decodeResource(getResources()
			, R.drawable.back);
		bubble = BitmapFactory
			.decodeResource(getResources(), R.drawable.bubble);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// ����ˮƽ�Ǳ���ͼƬ
		canvas.drawBitmap(back, 0, 0, null);
		// �������������������
		canvas.drawBitmap(bubble, bubbleX, bubbleY, null);
	}
}
