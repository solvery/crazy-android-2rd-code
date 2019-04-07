/**
 *
 */
package org.crazyit.service;

import java.util.Timer;
import java.util.TimerTask;

import org.crazyit.service.ICat.Stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

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
public class AidlService extends Service
{
	private CatBinder catBinder;
	Timer timer = new Timer();
	String[] colors = new String[]{
		"��ɫ",
		"��ɫ",
		"��ɫ"
	};
	double[] weights = new double[]{
		2.3,
		3.1,
		1.58
	};
	private String color;
	private double weight;
	// �̳�Stub��Ҳ����ʵ�ֶ�ICat�ӿڣ���ʵ����IBinder�ӿ�
	public class CatBinder extends Stub
	{
		@Override
		public String getColor() throws RemoteException
		{
			return color;
		}
		@Override
		public double getWeight() throws RemoteException
		{
			return weight;
		}
	}
	@Override
	public void onCreate()
	{
		super.onCreate();
		catBinder = new CatBinder();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				// ����ظı�Service�����color��weight���Ե�ֵ��
				int rand = (int)(Math.random() * 3);
				color = colors[rand];
				weight = weights[rand];
				System.out.println("--------" + rand);
			}
		} , 0 , 800);
	}
	@Override
	public IBinder onBind(Intent arg0)
	{
		/* ����catBinder����
		 * �ڰ󶨱���Service������£���catBinder�����ֱ��
		 * �����ͻ��˵�ServiceConnection����
		 * ��onServiceConnected�����ĵڶ���������
		 * �ڰ�Զ��Service������£�ֻ��catBinder����Ĵ���
		 * �����ͻ��˵�ServiceConnection����
		 * ��onServiceConnected�����ĵڶ���������
		 */
		return catBinder; //��
	}
	@Override
	public void onDestroy()
	{
		timer.cancel();
	}
}
