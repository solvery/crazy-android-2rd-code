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
			"疯狂Java讲义",
			"轻量级Java EE企业应用实战",
			"疯狂Android讲义",
			"疯狂Ajax讲义"
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
		// 调用next方法显示下一个字符串
		next(null);
	}
	// 事件处理函数，控制显示下一个字符串
	public void next(View source)
	{
		textSwitcher.setText(strs[curStr++ % strs.length]);  //①
	}
}
