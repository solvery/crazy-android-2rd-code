package org.crazyit.image;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
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
public class WarpTest extends Activity
{
	private Bitmap bitmap;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this, R.drawable.jinta));
	}

	private class MyView extends View
	{
		// ������������������������ָ����ͼƬ���������϶�������Ϊ20��
		private final int WIDTH = 20;
		private final int HEIGHT = 20;
		// ��¼��ͼƬ�ϰ���441������
		private final int COUNT = (WIDTH + 1) * (HEIGHT + 1);
		// ����һ�����飬����Bitmap�ϵ�21 * 21���������
		private final float[] verts = new float[COUNT * 2];
		// ����һ�����飬��¼Bitmap�ϵ�21 * 21���㾭��Ť���������
		// ��ͼƬ����Ť���Ĺؼ������޸ĸ�������Ԫ�ص�ֵ��
		private final float[] orig = new float[COUNT * 2];

		public MyView(Context context, int drawableId)
		{
			super(context);
			setFocusable(true);
			// ����ָ����Դ����ͼƬ
			bitmap = BitmapFactory.decodeResource(getResources()
				, drawableId);
			// ��ȡͼƬ��ȡ��߶�
			float bitmapWidth = bitmap.getWidth();
			float bitmapHeight = bitmap.getHeight();
			int index = 0;
			for (int y = 0; y <= HEIGHT; y++)
			{
				float fy = bitmapHeight * y / HEIGHT;
				for (int x = 0; x <= WIDTH; x++)
				{
					float fx = bitmapWidth * x / WIDTH;
					// ��ʼ��orig��verts���顣 ��ʼ����orig��verts
					// ����������ȵر�����21 * 21�����x,y����
					orig[index * 2 + 0] = verts[index * 2 + 0] = fx;
					orig[index * 2 + 1] = verts[index * 2 + 1] = fy;
					index += 1;
				}
			}
			// ���ñ���ɫ
			setBackgroundColor(Color.WHITE);
		}

		@Override
		protected void onDraw(Canvas canvas)
		{
			//��bitmap��verts�������Ť��
			//�ӵ�һ���㣨�ɵ�5������0���ƣ���ʼŤ��
			canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts
				, 0, null, 0,null);
		}

		// ���߷��������ڸ��ݴ����¼���λ�ü���verts�������Ԫ�ص�ֵ
		private void warp(float cx, float cy)
		{
			for (int i = 0; i < COUNT * 2; i += 2)
			{
				float dx = cx - orig[i + 0];
				float dy = cy - orig[i + 1];
				float dd = dx * dx + dy * dy;
				// ����ÿ��������뵱ǰ�㣨cx��cy��֮��ľ���
				float d = (float) Math.sqrt(dd);
				// ����Ť���ȣ����뵱ǰ�㣨cx��cy��ԽԶ��Ť����ԽС
				float pull = 80000 / ((float) (dd * d));
				// ��verts���飨����bitmap��21 * 21���㾭��Ť��������꣩���¸�ֵ
				if (pull >= 1)
				{
					verts[i + 0] = cx;
					verts[i + 1] = cy;
				}
				else
				{
					// ���Ƹ����������¼�������ƫ��
					verts[i + 0] = orig[i + 0] + dx * pull;
					verts[i + 1] = orig[i + 1] + dy * pull;
				}
			}
			// ֪ͨView����ػ�
			invalidate();
		}

		@Override
		public boolean onTouchEvent(MotionEvent event)
		{
			// ����warp�������ݴ������¼����������Ť��verts����
			warp(event.getX(), event.getY());
			return true;
		}
	}
}