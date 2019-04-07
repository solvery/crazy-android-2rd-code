/**
 *
 */
package org.crazyit.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
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
	public MyView(Context context, AttributeSet set)
	{
		super(context, set);
	}
	@Override
	// ��д�÷��������л�ͼ
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// �����Ż������Ƴɰ�ɫ
		canvas.drawColor(Color.WHITE);
		Paint paint = new Paint();
		// ȥ���
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(3);
		// ����Բ��
		canvas.drawCircle(40, 40, 30, paint);
		// ����������
		canvas.drawRect(10, 80, 70, 140, paint);
		// ���ƾ���
		canvas.drawRect(10, 150, 70, 190, paint);
		RectF re1 = new RectF(10, 200, 70, 230);
		// ����Բ�Ǿ���
		canvas.drawRoundRect(re1, 15, 15, paint);
		RectF re11 = new RectF(10, 240, 70, 270);
		// ������Բ
		canvas.drawOval(re11, paint);
		// ����һ��Path���󣬷�ճ�һ�������Ρ�
		Path path1 = new Path();
		path1.moveTo(10, 340);
		path1.lineTo(70, 340);
		path1.lineTo(40, 290);
		path1.close();
		// ����Path���л��ƣ�����������
		canvas.drawPath(path1, paint);
		// ����һ��Path���󣬷�ճ�һ������Ρ�
		Path path2 = new Path();
		path2.moveTo(26, 360);
		path2.lineTo(54, 360);
		path2.lineTo(70, 392);
		path2.lineTo(40, 420);
		path2.lineTo(10, 392);
		path2.close();
		// ����Path���л��ƣ����������
		canvas.drawPath(path2, paint);
		// ----------�������������----------
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.RED);
		canvas.drawCircle(120, 40, 30, paint);
		//����������
		canvas.drawRect(90, 80, 150, 140, paint);
		//���ƾ���
		canvas.drawRect(90, 150, 150, 190, paint);
		RectF re2 = new RectF(90, 200, 150, 230);
		//����Բ�Ǿ���
		canvas.drawRoundRect(re2, 15, 15, paint);
		RectF re21 = new RectF(90, 240, 150, 270);
		// ������Բ
		canvas.drawOval(re21, paint);
		Path path3 = new Path();
		path3.moveTo(90, 340);
		path3.lineTo(150, 340);
		path3.lineTo(120, 290);
		path3.close();
		//����������
		canvas.drawPath(path3, paint);
		Path path4 = new Path();
		path4.moveTo(106, 360);
		path4.lineTo(134, 360);
		path4.lineTo(150, 392);
		path4.lineTo(120, 420);
		path4.lineTo(90, 392);
		path4.close();
		//���������
		canvas.drawPath(path4, paint);
		// ----------���ý����������----------
		// ΪPaint���ý�����
		Shader mShader = new LinearGradient(0, 0, 40, 60
			, new int[] {
			Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW }
			, null , Shader.TileMode.REPEAT);
		paint.setShader(mShader);
		//������Ӱ
		paint.setShadowLayer(45 , 10 , 10 , Color.GRAY);
		// ����Բ��
		canvas.drawCircle(200, 40, 30, paint);
		// ����������
		canvas.drawRect(170, 80, 230, 140, paint);
		// ���ƾ���
		canvas.drawRect(170, 150, 230, 190, paint);
		RectF re3 = new RectF(170, 200, 230, 230);
		// ����Բ�Ǿ���
		canvas.drawRoundRect(re3, 15, 15, paint);
		RectF re31 = new RectF(170, 240, 230, 270);
		// ������Բ
		canvas.drawOval(re31, paint);
		Path path5 = new Path();
		path5.moveTo(170, 340);
		path5.lineTo(230, 340);
		path5.lineTo(200, 290);
		path5.close();
		// ����Path���л��ƣ�����������
		canvas.drawPath(path5, paint);
		Path path6 = new Path();
		path6.moveTo(186, 360);
		path6.lineTo(214, 360);
		path6.lineTo(230, 392);
		path6.lineTo(200, 420);
		path6.lineTo(170, 392);
		path6.close();
		// ����Path���л��ƣ����������
		canvas.drawPath(path6, paint);
		// ----------�����ַ���С�����----------
		paint.setTextSize(24);
		paint.setShader(null);
		// ����7���ַ���
		canvas.drawText(getResources().getString(R.string.circle), 240, 50,
			paint);
		canvas.drawText(getResources().getString(R.string.square), 240, 120,
			paint);
		canvas.drawText(getResources().getString(R.string.rect), 240, 175,
			paint);
		canvas.drawText(getResources().getString(R.string.round_rect), 230,
			220, paint);
		canvas.drawText(getResources().getString(R.string.oval), 240,
			260, paint);
		canvas.drawText(getResources().getString(R.string.triangle), 240, 325,
			paint);
		canvas.drawText(getResources().getString(R.string.pentagon), 240, 390,
			paint);
	}
}