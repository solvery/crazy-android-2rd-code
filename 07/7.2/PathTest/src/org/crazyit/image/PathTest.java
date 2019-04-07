package org.crazyit.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.SumPathEffect;
import android.graphics.PathEffect;
import android.os.Bundle;
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
public class PathTest extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
	}

	class MyView extends View
	{
		float phase;
		PathEffect[] effects = new PathEffect[7];
		int[] colors;
		private Paint paint;
		Path path;

		public MyView(Context context)
		{
			super(context);
			paint = new Paint();
			paint.setStyle(Paint.Style.STROKE);
			paint.setStrokeWidth(4);
			// ����������ʼ��Path
			path = new Path();
			path.moveTo(0, 0);
			for (int i = 1; i <= 15; i++)
			{
				// ����15���㣬����������ǵ�Y���ꡣ������������һ��Path
				path.lineTo(i * 20, (float) Math.random() * 60);
			}
			// ��ʼ��7����ɫ
			colors = new int[] { Color.BLACK, Color.BLUE, Color.CYAN,
				Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW };
			// -----------���濪ʼ��ʼ��7��·��Ч��----------
			// ��ʹ��·��Ч����
			effects[0] = null;
			// ʹ��CornerPathEffect·��Ч��
			effects[1] = new CornerPathEffect(10);
			// ��ʼ��DiscretePathEffect
			effects[2] = new DiscretePathEffect(3.0f, 5.0f);			
		}

		@Override
		protected void onDraw(Canvas canvas)
		{
			// ���������ɰ�ɫ
			canvas.drawColor(Color.WHITE);
			// ��ʼ��DashPathEffect��DashPathEffect�ж���Ч��
			effects[3] = new DashPathEffect(new float[] { 20, 10, 5, 10 },
					phase);
			// ��ʼ��PathDashPathEffect��PathDashPathEffect�ж���Ч��
			Path p = new Path();
			p.addRect(0, 0, 8, 8, Path.Direction.CCW);
			effects[4] = new PathDashPathEffect(p, 12, phase,
					PathDashPathEffect.Style.ROTATE);
			// ��ʼ��PathDashPathEffect
			effects[5] = new ComposePathEffect(effects[2], effects[4]);
			effects[6] = new SumPathEffect(effects[4], effects[3]);			
			// ��Canvasִ������任��������������λ�ơ���8��8����ʼ����
			canvas.translate(8, 8);
			// ����ʹ��7�в�ͬ·��Ч����7�ֲ�ͬ����ɫ������·��
			for (int i = 0; i < effects.length; i++)
			{
				paint.setPathEffect(effects[i]);
				paint.setColor(colors[i]);
				canvas.drawPath(path, paint);
				canvas.translate(0, 60);
			}
			// �ı�phaseֵ���γɶ���Ч��
			phase += 1;
			invalidate();
		}
	}
}