package org.crazyit.net;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
public class MultiThreadClient extends Activity
{
	// ��������ϵ������ı���
	EditText input;
	TextView show;
	// ��������ϵ�һ����ť
	Button send;
	Handler handler;
	// �����������ͨ�ŵ����߳�
	ClientThread clientThread;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		input = (EditText) findViewById(R.id.input);
		send = (Button) findViewById(R.id.send);
		show = (TextView) findViewById(R.id.show);
		handler = new Handler() //��
		{
			@Override
			public void handleMessage(Message msg)
			{
				// �����Ϣ���������߳�
				if (msg.what == 0x123)
				{
					// ����ȡ������׷����ʾ���ı�����
					show.append("\n" + msg.obj.toString());
				}
			}
		};
		clientThread = new ClientThread(handler);
		// �ͻ�������ClientThread�̴߳����������ӡ���ȡ���Է�����������
		new Thread(clientThread).start(); //��
		send.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					// ���û����·��Ͱ�ť�󣬽��û���������ݷ�װ��Message��
					// Ȼ���͸����̵߳�Handler
					Message msg = new Message();
					msg.what = 0x345;
					msg.obj = input.getText().toString();
					clientThread.revHandler.sendMessage(msg);
					// ���input�ı���
					input.setText("");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}