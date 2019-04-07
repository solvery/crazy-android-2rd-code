package org.crazyit.ui;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * Description: <br/>
 * site: <a href="http://www.crazyit.org">crazyit.org</a> <br/>
 * Copyright (C), 2001-2014, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class NotificationTest extends Activity
{
	static final int NOTIFICATION_ID = 0x123;
	NotificationManager nm;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡϵͳ��NotificationManager����
		nm = (NotificationManager) 
			getSystemService(NOTIFICATION_SERVICE);
	}

	// Ϊ����֪ͨ�İ�ť�ĵ���¼������¼�������
	public void send(View source)
	{
		// ����һ����������Activity��Intent
		Intent intent = new Intent(NotificationTest.this
			, OtherActivity.class);
		PendingIntent pi = PendingIntent.getActivity(
			NotificationTest.this, 0, intent, 0);
		Notification notify = new Notification.Builder(this)
			// ���ô򿪸�֪ͨ����֪ͨ�Զ���ʧ
			.setAutoCancel(true)
			// ������ʾ��״̬����֪ͨ��ʾ��Ϣ
			.setTicker("������Ϣ")
			// ����֪ͨ��ͼ��
			.setSmallIcon(R.drawable.notify)
			// ����֪ͨ���ݵı���
			.setContentTitle("һ����֪ͨ")
			// ����֪ͨ����
			.setContentText("��ϲ�㣬����н�ˣ���������20%!")
			// // ����ʹ��ϵͳĬ�ϵ�������Ĭ��LED��
			// .setDefaults(Notification.DEFAULT_SOUND
			// |Notification.DEFAULT_LIGHTS)
			// ����֪ͨ���Զ�������
			.setSound(Uri.parse("android.resource://org.crazyit.ui/"
				+ R.raw.msg))
			.setWhen(System.currentTimeMillis())
			// ���֪ͨ��Ҫ���������Intent
			.setContentIntent(pi).build();
		// ����֪ͨ
		nm.notify(NOTIFICATION_ID, notify);
	}

	// Ϊɾ��֪ͨ�İ�ť�ĵ���¼������¼�������
	public void del(View v)
	{
		// ȡ��֪ͨ
		nm.cancel(NOTIFICATION_ID);
	}
}