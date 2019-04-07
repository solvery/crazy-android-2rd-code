package org.crazyit.net;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
public class CallWs extends Activity
{
	final static String SERVICE_NS = "http://lee/";
	final static String SERVICE_URL = "http://192.168.1.88:9999/crazyit";
	private EditText txt1;
	private EditText txt2;
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			switch(msg.what)
			{
				case 0x123:
					txt1.setText(msg.obj.toString());
					break;
				case 0x234:
					txt2.setText(msg.obj.toString());
					break;
			}
		}
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txt1 = (EditText) findViewById(R.id.txt1);
		txt2 = (EditText) findViewById(R.id.txt2);
		// ���õķ���
		String methodName = "getUserList";
		// ����HttpTransportSE�������
		final HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);  //��
		ht.debug = true;
		// ʹ��SOAP1.1Э�鴴��Envelop����
		final SoapSerializationEnvelope envelope = new 
			SoapSerializationEnvelope(SoapEnvelope.VER11);  //��
		// ʵ����SoapObject����
		SoapObject soapObject = new SoapObject(SERVICE_NS, methodName); //��
		soapObject.addProperty("arg0", "�ͻ��˲���:");  //��
		// ��soapObject��������Ϊ SoapSerializationEnvelope����Ĵ���SOAP��Ϣ
		envelope.bodyOut = soapObject;  //��
		new Thread()
		{
			public void run()
			{
				try
				{
					// ����Web Service
					ht.call(null,  envelope);  //��
					if (envelope.getResponse() != null)
					{
						// ��ȡ��������Ӧ���ص�SOAP��Ϣ
						SoapObject result = (SoapObject) envelope.bodyIn; //��
						// ���������Ǵ�SoapObject�����н�����Ӧ���ݵĹ����ˡ�
						SoapObject detail1 = (SoapObject) result
							.getProperty(0);
						SoapObject detail2 = (SoapObject) result
							.getProperty(1);
						StringBuilder person1 = new StringBuilder();
						person1.append("�û�����");
						person1.append(detail1.getProperty(3));
						person1.append("\n����");
						person1.append(detail1.getProperty(0));
						person1.append("\n��ߣ�");
						person1.append(detail1.getProperty(1));
						Message msg = new Message();
						msg.what = 0x123;
						msg.obj = person1.toString();
						handler.sendMessage(msg);
						StringBuilder person2 = new StringBuilder();
						person2.append("�û�����");
						person2.append(detail2.getProperty(3));
						person2.append("\n���룺");
						person2.append(detail2.getProperty(0));
						person2.append("\n��ߣ�");
						person2.append(detail2.getProperty(1));
						Message msg2 = new Message();
						msg2.what = 0x234;
						msg2.obj = person2.toString();
						handler.sendMessage(msg2);
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				catch (XmlPullParserException e)
				{
					e.printStackTrace();
				}
			}
		}.start();	
	}
}