package org.crazyit.service;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

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
public class IntentServiceTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void startService(View source)
	{
		// 创建需要启动的Service的Intent
		Intent intent = new Intent(this, MyService.class);
		// 启动Service
		startService(intent);
	}

	public void startIntentService(View source)
	{
		// 创建需要启动的IntentService的Intent
		Intent intent = new Intent(this, MyIntentService.class);
		// 启动IntentService
		startService(intent);
	}
}
