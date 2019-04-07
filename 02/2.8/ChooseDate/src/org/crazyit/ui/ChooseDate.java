package org.crazyit.ui;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

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
public class ChooseDate extends Activity
{
	// ����5����¼��ǰʱ��ı���
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		DatePicker datePicker = (DatePicker)
			findViewById(R.id.datePicker);
		TimePicker timePicker = (TimePicker) 
			findViewById(R.id.timePicker);
		// ��ȡ��ǰ���ꡢ�¡��ա�Сʱ������
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR);
		minute = c.get(Calendar.MINUTE);
		// ��ʼ��DatePicker�������ʼ��ʱָ��������
		datePicker.init(year, month, day, new OnDateChangedListener()
		{

			@Override
			public void onDateChanged(DatePicker arg0, int year
					, int month, int day)
			{
				ChooseDate.this.year = year;
				ChooseDate.this.month = month;
				ChooseDate.this.day = day;
				// ��ʾ��ǰ���ڡ�ʱ��
				showDate(year, month, day, hour, minute);
			}
		});
		// ΪTimePickerָ��������
		timePicker.setOnTimeChangedListener(new OnTimeChangedListener()
		{

			@Override
			public void onTimeChanged(TimePicker view
					, int hourOfDay, int minute)
			{
				ChooseDate.this.hour = hourOfDay;
				ChooseDate.this.minute = minute;
				// ��ʾ��ǰ���ڡ�ʱ��
				showDate(year, month, day, hour, minute);
				
			}
		});
	}

	// ������EditText����ʾ��ǰ���ڡ�ʱ��ķ���
	private void showDate(int year, int month
			, int day, int hour, int minute)
	{
		EditText show = (EditText) findViewById(R.id.show);
		show.setText("���Ĺ�������Ϊ��" + year + "��" 
				+ (month + 1) + "��" + day + "��  "
				+ hour + "ʱ" + minute + "��");
	}
}