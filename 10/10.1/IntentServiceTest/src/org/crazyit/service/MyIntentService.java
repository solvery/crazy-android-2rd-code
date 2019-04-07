package org.crazyit.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class MyIntentService extends IntentService
{
	public MyIntentService()
	{
		super("MyIntentService");
	}

	// IntentService��ʹ�õ������߳���ִ�и÷����Ĵ���
	@Override
	protected void onHandleIntent(Intent intent)
	{
		// �÷����ڿ���ִ���κκ�ʱ���񣬱��������ļ��ȣ��˴�ֻ�����߳���ͣ20��
		long endTime = System.currentTimeMillis() + 20 * 1000;
		System.out.println("onStart");
		while (System.currentTimeMillis() < endTime)
		{
			synchronized (this)
			{
				try
				{
					wait(endTime - System.currentTimeMillis());
				}
				catch (Exception e)
				{
				}
			}
		}
		System.out.println("---��ʱ����ִ�����---");
	}
}
