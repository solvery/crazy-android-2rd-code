package org.crazyit.intent;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

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
public class IntentTab extends TabActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ��Activity�����TabHost���
		TabHost tabHost = getTabHost();
		// ʹ��Intent��ӵ�һ��Tabҳ��
		tabHost.addTab(tabHost
			.newTabSpec("tab1")
			.setIndicator("�ѽӵ绰",
				getResources().getDrawable(R.drawable.ic_launcher))
			.setContent(new Intent(this, BeCalledActivity.class)));
		// ʹ��Intent��ӵڶ���Tabҳ��
		tabHost.addTab(tabHost.newTabSpec("tab1")
			.setIndicator("�����绰")
			.setContent(new Intent(this, CalledActivity.class)));
		// ʹ��Intent��ӵ�����Tabҳ��
		tabHost.addTab(tabHost.newTabSpec("tab1")
			.setIndicator("δ�ӵ绰")
			.setContent(new Intent(this, NoCallActivity.class)));
	}
}