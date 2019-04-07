package org.crazyit.res;

import java.util.Timer;
import java.util.TimerTask;

import org.crazyit.res.R;

import android.app.Activity;
import android.graphics.drawable.ClipDrawable;
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
public class ClipDrawableTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ImageView imageview = (ImageView) findViewById(R.id.image);
		// ��ȡͼƬ����ʾ��ClipDrawable����
		final ClipDrawable drawable = (ClipDrawable)
			imageview.getDrawable();
		final Handler handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				// �������Ϣ�Ǳ����������͵�
				if (msg.what == 0x1233)
				{
					// �޸�ClipDrawable��levelֵ
					drawable.setLevel(drawable.getLevel() + 200);
				}
			}
		};
		final Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				Message msg = new Message();
				msg.what = 0x1233;
				// ������Ϣ��֪ͨӦ���޸�ClipDrawable�����levelֵ��
				handler.sendMessage(msg);
				// ȡ����ʱ��
				if (drawable.getLevel() >= 10000)
				{
					timer.cancel();
				}
			}
		}, 0, 300);
	}
}