/**
 *
 */
package org.crazyit.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class BindService extends Service
{
	private int count;
	private boolean quit;
	// ����onBinder���������صĶ���
	private MyBinder binder = new MyBinder();
	// ͨ���̳�Binder��ʵ��IBinder��
	public class MyBinder extends Binder //��
	{
		public int getCount()
		{
			// ��ȡService������״̬��count
			return count;
		}
	}
	// ����ʵ�ֵķ������󶨸�Serviceʱ�ص��÷���
	@Override
	public IBinder onBind(Intent intent)
	{
		System.out.println("Service is Binded");
		// ����IBinder����
		return binder;
	}
	// Service������ʱ�ص��÷�����
	@Override
	public void onCreate()
	{
		super.onCreate();
		System.out.println("Service is Created");
		// ����һ���̡߳���̬���޸�count״ֵ̬
		new Thread()
		{
			@Override
			public void run()
			{
				while (!quit)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
					}
					count++;
				}
			}
		}.start();
	}
	// Service���Ͽ�����ʱ�ص��÷���
	@Override
	public boolean onUnbind(Intent intent)
	{
		System.out.println("Service is Unbinded");
		return true;
	}
	// Service���ر�֮ǰ�ص��÷�����
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		this.quit = true;
		System.out.println("Service is Destroyed");
	}
}