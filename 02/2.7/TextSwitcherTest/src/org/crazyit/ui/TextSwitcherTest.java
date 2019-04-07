package org.crazyit.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.app.Activity;
import android.graphics.Color;

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
public class TextSwitcherTest extends Activity
{
	TextSwitcher textSwitcher;
	String[] strs = new String[]
		{
			"���Java����",
			"������Java EE��ҵӦ��ʵս",
			"���Android����",
			"���Ajax����"
		};
	int curStr;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
		textSwitcher.setFactory(new ViewSwitcher.ViewFactory()
		{
			public View makeView()
			{
				TextView tv = new TextView(TextSwitcherTest.this);
				tv.setTextSize(40);
				tv.setTextColor(Color.MAGENTA);
				return tv;
			}
		});
		// ����next������ʾ��һ���ַ���
		next(null);
	}
	// �¼���������������ʾ��һ���ַ���
	public void next(View source)
	{
		textSwitcher.setText(strs[curStr++ % strs.length]);  //��
	}
}
