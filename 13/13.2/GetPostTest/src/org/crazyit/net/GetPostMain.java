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
public class GetPostMain extends Activity
{
	Button get , post;
	TextView show;
	// �����������Ӧ���ַ���
	String response;
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if(msg.what == 0x123)
			{
				// ����show�����ʾ��������Ӧ
				show.setText(response);
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		get = (Button) findViewById(R.id.get);
		post = (Button) findViewById(R.id.post);
		show = (TextView)findViewById(R.id.show);
		get.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new Thread()
				{
					@Override
					public void run()
					{
						response = GetPostUtil.sendGet(
							"http://192.168.1.88:8888/abc/a.jsp"
							, null);
						// ������Ϣ֪ͨUI�̸߳���UI���
						handler.sendEmptyMessage(0x123);
					}
				}.start();
			}
		});
		post.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				new Thread()
				{
					@Override
					public void run()
					{
						response = GetPostUtil.sendPost(
							"http://192.168.1.88:8888/abc/login.jsp"
							, "name=crazyit.org&pass=leegang");
					}
				}.start();
				// ������Ϣ֪ͨUI�̸߳���UI���
				handler.sendEmptyMessage(0x123);
			}
		});
	}
}