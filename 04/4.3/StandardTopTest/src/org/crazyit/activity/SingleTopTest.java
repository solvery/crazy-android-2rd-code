package org.crazyit.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Description: <br/>
 * site: <a href="http://www.crazyit.org">crazyit.org</a> <br/>
 * Copyright (C), 2001-2014, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class SingleTopTest extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		this.setContentView(layout);
		// ����һ��TextView����ʾ��Activity��������Task ID��
		TextView tv = new TextView(this);
		tv.setText("ActivityΪ��" + this.toString()
			+ "\n" + "��Task IDΪ:" + this.getTaskId());
		Button button = new Button(this);
		button.setText("����SecondActivity");
		layout.addView(tv);
		layout.addView(button);
		// Ϊbutton����¼�����������������Activityʱ�ٴ�����StandardTest
		button.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// ��������SecondActivity��Intent
				Intent intent = new Intent(SingleTopTest.this
					, SecondActivity.class);
				startActivity(intent);
			}
		});
	}
}
