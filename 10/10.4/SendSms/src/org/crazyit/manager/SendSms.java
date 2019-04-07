package org.crazyit.manager;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
public class SendSms extends Activity
{
	EditText number, content;
	Button send;
	SmsManager sManager;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡSmsManager
		sManager = SmsManager.getDefault();
		// ��ȡ��������ϵ������ı���Ͱ�ť
		number = (EditText) findViewById(R.id.number);
		content = (EditText) findViewById(R.id.content);
		send = (Button) findViewById(R.id.send);
		// Ϊsend��ť�ĵ����¼��󶨼�����
		send.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ����һ��PendingIntent����
				PendingIntent pi = PendingIntent.getActivity(
					SendSms.this, 0, new Intent(), 0);
				// ���Ͷ���
				sManager.sendTextMessage(number.getText().toString(),
					null, content.getText().toString(), pi, null);
				// ��ʾ���ŷ������
				Toast.makeText(SendSms.this, "���ŷ������", 8000).show();
			}
		});
	}
}