package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

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
public class CustomView extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ�����ļ��е�LinearLayout����
		LinearLayout root = (LinearLayout) findViewById(R.id.root);
		// ����DrawView���
		final DrawView draw = new DrawView(this);
		// �����Զ������������ȡ��߶�
		draw.setMinimumWidth(300);
		draw.setMinimumHeight(500);
		root.addView(draw);
	}
}