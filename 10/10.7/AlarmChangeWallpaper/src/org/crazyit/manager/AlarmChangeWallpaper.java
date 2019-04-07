package org.crazyit.manager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

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
public class AlarmChangeWallpaper extends Activity
{
	// ����AlarmManager����
	AlarmManager aManager;
	Button start, stop;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
		aManager = (AlarmManager) getSystemService(
			Service.ALARM_SERVICE);
		// ָ������ChangeService���
		Intent intent = new Intent(AlarmChangeWallpaper.this,
			ChangeService.class);
		// ����PendingIntent����
		final PendingIntent pi = PendingIntent.getService(
			AlarmChangeWallpaper.this, 0, intent, 0);
		start.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ����ÿ��5��ִ��pi��������һ��
				aManager.setRepeating(AlarmManager.RTC_WAKEUP
						, 0, 5000, pi);
				start.setEnabled(false);
				stop.setEnabled(true);
				Toast.makeText(AlarmChangeWallpaper.this
					, "��ֽ��ʱ���������ɹ���",
					Toast.LENGTH_SHORT).show();
			}
		});
		stop.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				start.setEnabled(true);
				stop.setEnabled(false);
				// ȡ����pi�ĵ���
				aManager.cancel(pi);
			}
		});

	}
}