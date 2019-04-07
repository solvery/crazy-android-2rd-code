package org.crazyit.image;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
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
public class Butterfly extends Activity
{
	// ��¼����ImageView��ǰ��λ��
	private float curX = 0;
	private float curY = 30;
	// ��¼����ImageView��һ��λ�õ�����
	float nextX = 0;
	float nextY = 0;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ��ʾ������ImageView���
		final ImageView imageView = (ImageView) 
			findViewById(R.id.butterfly);
		final Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				if (msg.what == 0x123)
				{
					// ������һֱ���ҷ�
					if (nextX > 320)
					{
						curX = nextX = 0;
					}
					else
					{
						nextX += 8;
					}
					// �����Ͽ����������
					nextY = curY + (float) (Math.random() * 10 - 5);
					// ������ʾ������ImageView����λ�Ƹı�
					TranslateAnimation anim = new TranslateAnimation(
						curX, nextX, curY, nextY);
					curX = nextX;
					curY = nextY;
					anim.setDuration(200);
					// ��ʼλ�ƶ���
					imageView.startAnimation(anim); // ��
				}
			}
		};
		final AnimationDrawable butterfly = (AnimationDrawable)
			imageView.getBackground();
		imageView.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// ��ʼ���ź���������֡����
				butterfly.start();// ��
				// ͨ������������ÿ0.2������һ��TranslateAnimation����
				new Timer().schedule(new TimerTask()
				{
					@Override
					public void run()
					{
						handler.sendEmptyMessage(0x123);
					}

				}, 0, 200);
			}
		});
	}
}