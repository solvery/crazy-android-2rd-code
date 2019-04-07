/**
 *
 */
package org.crazyit.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
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
	// ��ʼ��ͼƬ��Դ
	private Bitmap bitmap;
	// Matrix ʵ��
	private Matrix matrix = new Matrix();
	// ������б��
	private float sx = 0.0f;
	// λͼ��͸�
	private int width, height;
	// ���ű���
	private float scale = 1.0f;
	// �ж����Ż�����ת
	private boolean isScale = false;
	public MyView(Context context , AttributeSet set)
	{
		super(context , set);
		// ���λͼ
		bitmap = ((BitmapDrawable) context.getResources().getDrawable(
			R.drawable.a)).getBitmap();
		// ���λͼ��
		width = bitmap.getWidth();
		// ���λͼ��
		height = bitmap.getHeight();
		// ʹ��ǰ��ͼ��ý���
		this.setFocusable(true);
	}
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// ����Matrix
		matrix.reset();
		if (!isScale)
		{
			// ��תMatrix
			matrix.setSkew(sx, 0);
		}
		else
		{
			// ����Matrix
			matrix.setScale(scale, scale);
		}
		// ����ԭʼλͼ��Matrix������ͼƬ
		Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, width, height,
			matrix, true);
		// ������λͼ
		canvas.drawBitmap(bitmap2, matrix, null);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch(keyCode)
		{
			// ������б
			case KeyEvent.KEYCODE_A:
				isScale = false;
				sx += 0.1;
				postInvalidate();
				break;
			// ������б
			case KeyEvent.KEYCODE_D:
				isScale = false;
				sx -= 0.1;
				postInvalidate();
				break;
			// �Ŵ�
			case KeyEvent.KEYCODE_W:
				isScale = true;
				if (scale < 2.0)
					scale += 0.1;
				postInvalidate();
				break;
			// ��С
			case KeyEvent.KEYCODE_S:
				isScale = true;
				if (scale > 0.5)
					scale -= 0.1;
				postInvalidate();
				break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
