package org.crazyit.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

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
public class SimpleClient extends Activity
{
	EditText show;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		show = (EditText) findViewById(R.id.show);
		new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					// �������ӵ�Զ�̷�������Socket
					Socket socket = new Socket("192.168.1.88" , 30000);  //��
					// ��Socket��Ӧ����������װ��BufferedReader
					BufferedReader br = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
					// ������ͨIO����
					String line = br.readLine();
					show.setText("���Է����������ݣ�" + line);
					// �ر���������socket
					br.close();
					socket.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}.start();
	}
}