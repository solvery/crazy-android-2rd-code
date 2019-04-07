package org.crazyit.event;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
public class HandlerTest extends Activity
{
	// ������������ʾ��ͼƬ��ID
	int[] imageIds = new int[]
	{
		R.drawable.java,
		R.drawable.ee,
		R.drawable.ajax,
		R.drawable.xml,
		R.drawable.classic
	};
	int currentImageId = 0;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ImageView show = (ImageView) findViewById(R.id.show);
		final Handler myHandler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				// �������Ϣ�Ǳ����������͵�
				if (msg.what == 0x1233)
				{
					// ��̬���޸�����ʾ��ͼƬ
					show.setImageResource(imageIds[currentImageId++
						% imageIds.length]);
				}
			}
		};
		// ����һ����ʱ�����øü�ʱ�������Ե�ִ��ָ������
		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				// ���Ϳ���Ϣ
				myHandler.sendEmptyMessage(0x1233);
			}
		}, 0, 1200);
	}
}