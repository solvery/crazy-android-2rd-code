package org.crazyit.ui;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;
import android.app.Activity;

/**
 * Description:
 * <br/>website: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class CalendarViewTest extends Activity
{
	CalendarView cv;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		cv = (CalendarView)findViewById(R.id.calendarView);
		// 为CalendarView组件的日期改变事件添加事件监听器
		cv.setOnDateChangeListener(new OnDateChangeListener()
		{
			@Override
			public void onSelectedDayChange(CalendarView view, int year,
					int month, int dayOfMonth)
			{
				// 使用Toast显示用户选择的日期
				Toast.makeText(CalendarViewTest.this,
					"你生日是" + year + "年" + month + "月" 
					+ dayOfMonth + "日" ,
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
