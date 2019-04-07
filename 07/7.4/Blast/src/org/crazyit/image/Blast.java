package org.crazyit.image;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

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
public class Blast extends Activity
{
	private MyView myView;
	private AnimationDrawable anim;
	private MediaPlayer bomb;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ʹ��FrameLayout���ֹ�����������������Լ�����λ��
		FrameLayout frame = new FrameLayout(this);
		setContentView(frame);
		// ����ʹ�ñ���
		frame.setBackgroundResource(R.drawable.back);
		// ������Ч
		bomb = MediaPlayer.create(this, R.raw.bomb);
		myView = new MyView(this);
		// ����myView������ʾblast����
		myView.setBackgroundResource(R.anim.blast);
		// ����myViewĬ��Ϊ����
		myView.setVisibility(View.INVISIBLE);
		// ��ȡ��������
		anim = (AnimationDrawable) myView.getBackground();
		frame.addView(myView);
		frame.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View source, MotionEvent event)
			{
				// ֻ�������¼�������ÿ�β�����������Ч����
				if (event.getAction() == MotionEvent.ACTION_DOWN)
				{
					// ��ֹͣ��������
					anim.stop();
					float x = event.getX();
					float y = event.getY();
					// ����myView����ʾλ��
					myView.setLocation((int) y - 40, (int) x - 20);
					myView.setVisibility(View.VISIBLE);
					// ��������
					anim.start();
					// ������Ч
					bomb.start();
				}
				return false;
			}
		});
	}

	// ����һ���Զ���View�����Զ���View���ڲ��š���ը��Ч��
	class MyView extends ImageView
	{
		public MyView(Context context)
		{
			super(context);
		}

		// ����һ���������÷������ڿ���MyView����ʾλ��
		public void setLocation(int top, int left)
		{
			this.setFrame(left, top, left + 40, top + 40);
		}

		// ��д�÷�������������������ŵ����һ֡ʱ�����ظ�View
		@Override
		protected void onDraw(Canvas canvas) // ��
		{
			try
			{
				Field field = AnimationDrawable.class
						.getDeclaredField("mCurFrame");
				field.setAccessible(true);
				// ��ȡanim�����ĵ�ǰ֡
				int curFrame = field.getInt(anim);
				// ����Ѿ��������һ֡
				if (curFrame == anim.getNumberOfFrames() - 1)
				{
					// �ø�View����
					setVisibility(View.INVISIBLE);
				}
			}
			catch (Exception e)
			{
			}
			super.onDraw(canvas);
		}
	}
}