package org.crazyit.desktop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.RemoteViews;

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
public class LedClock extends AppWidgetProvider
{
	private Timer timer = new Timer();
	private AppWidgetManager appWidgetManager;
	private Context context;
	// ��0��9��Һ������ͼƬ���������
	private int[] digits = new int[]
	{
		R.drawable.su01,
		R.drawable.su02,
		R.drawable.su03,
		R.drawable.su04,
		R.drawable.su05,
		R.drawable.su06,
		R.drawable.su07,
		R.drawable.su08,
		R.drawable.su09,
		R.drawable.su10,
	};
	// ����ʾСʱ�����ӡ����ӵ�ImageView���������
	private int[] digitViews = new int[]
   	{
   		R.id.img01,
   		R.id.img02,
   		R.id.img04,
   		R.id.img05,
   		R.id.img07,
   		R.id.img08
   	};
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds)
	{
		System.out.println("--onUpdate--");
		this.appWidgetManager = appWidgetManager;
		this.context = context;
		// �����ʱ��
		timer = new Timer();
		// ���������Ե���
		timer.schedule(new TimerTask()
		{
			public void run()
			{
				// ���Ϳ���Ϣ��֪ͨ�������
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 1000);
	}

	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if (msg.what == 0x123)
			{
				RemoteViews views = new RemoteViews(context
						.getPackageName(), R.layout.main);
				// ����SimpleDateFormat����
				SimpleDateFormat df = new SimpleDateFormat(
						"HHmmss");
				// ����ǰʱ���ʽ����HHmmss����ʽ
				String timeStr = df.format(new Date());
				for(int i = 0 ; i < timeStr.length() ;i++)
				{
					// ����i�������ַ�ת��Ϊ��Ӧ������
					int num = timeStr.charAt(i) - 48;
					// ����i��ͼƬ����Ϊ��Ӧ��Һ������ͼƬ
					views.setImageViewResource(digitViews[i], digits[num]);
				}
				// ��AppWidgetProvider����ʵ����װ��ComponentName����
				ComponentName componentName = new ComponentName(context,
					LedClock.class);
				// ����AppWidgetManager��remoteViews��ӵ�ComponentName��
				appWidgetManager.updateAppWidget(componentName, views);
			}
			super.handleMessage(msg);
		}
	};
}