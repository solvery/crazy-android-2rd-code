package org.crazyit.manager;

import java.util.Calendar;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
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
public class AlarmTest extends Activity
{
	Button setTime;
	AlarmManager aManager;
	Calendar currentTime = Calendar.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ�������İ�ť
		setTime = (Button) findViewById(R.id.setTime);
		// ��ȡAlarmManager����
		aManager = (AlarmManager) getSystemService(
			Service.ALARM_SERVICE);
		// Ϊ���������塱��ť�󶨼�������
		setTime.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				Calendar currentTime = Calendar.getInstance();
				// ����һ��TimePickerDialogʵ������������ʾ������
				new TimePickerDialog(AlarmTest.this, 0, // �󶨼�����
					new TimePickerDialog.OnTimeSetListener()
					{
						@Override
						public void onTimeSet(TimePicker tp,
							int hourOfDay, int minute)
						{
							// ָ������AlarmActivity���
							Intent intent = new Intent(AlarmTest.this,
								AlarmActivity.class);
							// ����PendingIntent����
							PendingIntent pi = PendingIntent.getActivity(
								AlarmTest.this, 0, intent, 0);
							Calendar c = Calendar.getInstance();
							// �����û�ѡ��ʱ��������Calendar����
							c.set(Calendar.HOUR, hourOfDay);
							c.set(Calendar.MINUTE, minute);
							// ����AlarmManager����Calendar��Ӧ��ʱ������ָ�����
							aManager.set(AlarmManager.RTC_WAKEUP,
								c.getTimeInMillis(), pi);							
							// ��ʾ�������óɹ�����ʾ��Ϣ
							Toast.makeText(AlarmTest.this, "�������óɹ���"
								, Toast.LENGTH_SHORT).show();
						}
					}, currentTime.get(Calendar.HOUR_OF_DAY), currentTime
						.get(Calendar.MINUTE), false).show();
			}
		});
	}
}