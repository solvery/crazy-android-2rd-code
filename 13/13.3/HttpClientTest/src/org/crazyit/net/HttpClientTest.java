package org.crazyit.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
public class HttpClientTest extends Activity
{
	TextView response;
	HttpClient httpClient;
	Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			if(msg.what == 0x123)
			{
				// ʹ��response�ı�����ʾ��������Ӧ
				response.append(msg.obj.toString() + "\n");
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ����DefaultHttpClient����
		httpClient = new DefaultHttpClient();
		response = (TextView) findViewById(R.id.response);
	}
	public void accessSecret(View v)
	{
		response.setText("");
		new Thread()
		{
			@Override
			public void run()
			{
				// ����һ��HttpGet����
				HttpGet get = new HttpGet(
					"http://192.168.1.88:8888/foo/secret.jsp");  //��
				try
				{
					// ����GET����
					HttpResponse httpResponse = httpClient.execute(get);//��
					HttpEntity entity = httpResponse.getEntity();
					if (entity != null)
					{
						// ��ȡ��������Ӧ
						BufferedReader br = new BufferedReader(
							new InputStreamReader(entity.getContent()));
						String line = null;
						
						while ((line = br.readLine()) != null)
						{
							Message msg = new Message();
							msg.what = 0x123;
							msg.obj = line;
							handler.sendMessage(msg);
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}.start();
	}
	public void showLogin(View v)
	{
		// ���ص�¼����
		final View loginDialog = getLayoutInflater().inflate(
			R.layout.login, null);
		// ʹ�öԻ����û���¼ϵͳ
		new AlertDialog.Builder(HttpClientTest.this)
			.setTitle("��¼ϵͳ")
			.setView(loginDialog)
			.setPositiveButton("��¼",
			new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog,
					int which)
				{
					// ��ȡ�û�������û���������
					final String name = ((EditText) loginDialog
						.findViewById(R.id.name)).getText()
						.toString();
					final String pass = ((EditText) loginDialog
						.findViewById(R.id.pass)).getText()
						.toString();
					new Thread()
					{
						@Override
						public void run()
						{
							try
							{
								HttpPost post = new HttpPost("http://192.168"
									+ ".1.88:8888/foo/login.jsp");//��
								// ������ݲ��������Ƚ϶�Ļ����ԶԴ��ݵĲ������з�װ
								List<NameValuePair> params = new
									ArrayList<NameValuePair>();
								params.add(new BasicNameValuePair
									("name", name));
								params.add(new BasicNameValuePair
									("pass", pass));								
								// �����������
								post.setEntity(new UrlEncodedFormEntity(
									params, HTTP.UTF_8));
								// ����POST����
								HttpResponse response = httpClient
									.execute(post);  //��
								// ����������ɹ��ط�����Ӧ
								if (response.getStatusLine()
									.getStatusCode() == 200)
								{
									String msg = EntityUtils
										.toString(response.getEntity());
									Looper.prepare();
									// ��ʾ��¼�ɹ�
									Toast.makeText(HttpClientTest.this,
										msg, Toast.LENGTH_SHORT).show();
									Looper.loop();
								}
							}
							catch (Exception e)
							{
								e.printStackTrace();
							}
						}
					}.start();
				}
			}).setNegativeButton("ȡ��", null).show();
	}
}