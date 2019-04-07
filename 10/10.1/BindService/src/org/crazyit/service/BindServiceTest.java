package org.crazyit.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

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
public class BindServiceTest extends Activity
{
	Button bind, unbind, getServiceStatus;
	// ������������Service��IBinder����
	BindService.MyBinder binder;
	// ����һ��ServiceConnection����
	private ServiceConnection conn = new ServiceConnection()
	{
		// ����Activity��Service���ӳɹ�ʱ�ص��÷���
		@Override
		public void onServiceConnected(ComponentName name
			, IBinder service)
		{
			System.out.println("--Service Connected--");
			// ��ȡService��onBind���������ص�MyBinder����
			binder = (BindService.MyBinder) service; //��
		}

		// ����Activity��Service�Ͽ�����ʱ�ص��÷���
		@Override
		public void onServiceDisconnected(ComponentName name)
		{
			System.out.println("--Service Disconnected--");
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡ��������е�start��stop��getServiceStatus��ť
		bind = (Button) findViewById(R.id.bind);
		unbind = (Button) findViewById(R.id.unbind);
		getServiceStatus = (Button) findViewById(R.id.getServiceStatus);
		// ��������Service��Intent
		final Intent intent = new Intent();
		// ΪIntent����Action����
		intent.setAction("org.crazyit.service.BIND_SERVICE");
		bind.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��ָ��Serivce
				bindService(intent, conn, Service.BIND_AUTO_CREATE);
			}
		});
		unbind.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// �����Serivce
				unbindService(conn);
			}
		});
		getServiceStatus.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��ȡ������ʾService��countֵ
				Toast.makeText(BindServiceTest.this,
					"Serivce��countֵΪ��" + binder.getCount(),
					Toast.LENGTH_SHORT).show(); //��
			}
		});
	}
}