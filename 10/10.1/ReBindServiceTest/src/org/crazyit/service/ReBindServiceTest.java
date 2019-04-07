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
public class ReBindServiceTest extends Activity
{
	// ������������Service��IBinder����
	BindService.MyBinder binder;
	// ��������Service��Intent
	Intent intent;
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
	
	public void start(View source)
	{
		// ����intent��Ӧ��Service
		startService(intent);
	}
	
	public void bind(View source)
	{
		// ����intent��Ӧ��Service
		// ��ָ��Serivce
		bindService(intent, conn, Service.BIND_AUTO_CREATE);
	}
	
	public void unBind(View source)
	{
		// �����Serivce
		unbindService(conn);
	}	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��������Service��Intent
		intent = new Intent();
		// ΪIntent����Action����
		intent.setAction("org.crazyit.service.BIND_SERVICE");
	}
}