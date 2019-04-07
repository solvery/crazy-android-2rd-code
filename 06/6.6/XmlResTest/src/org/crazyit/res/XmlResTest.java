package org.crazyit.res;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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
public class XmlResTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡbn��ť����Ϊ�ð�ť���¼�������
		Button bn = (Button) findViewById(R.id.bn);
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ����XML��Դ��ID��ȡ��������Դ�Ľ�������
				// XmlResourceParser��XmlPullParser�����ࡣ
				XmlResourceParser xrp = getResources().getXml(R.xml.books);
				try
				{
					StringBuilder sb = new StringBuilder("");
					// ��û�е�XML�ĵ��Ľ�β��
					while (xrp.getEventType() 
						!= XmlResourceParser.END_DOCUMENT)
					{
						// ��������˿�ʼ��ǩ
						if (xrp.getEventType() == XmlResourceParser.START_TAG)
						{
							// ��ȡ�ñ�ǩ�ı�ǩ��
							String tagName = xrp.getName();
							// �������book��ǩ
							if (tagName.equals("book"))
							{
								// ��������������ȡ����ֵ
								String bookName = xrp.getAttributeValue(null,
									"price");
								sb.append("�۸�");
								sb.append(bookName);
								// ����������������ȡ����ֵ
								String bookPrice = xrp.getAttributeValue(1);
								sb.append("	�������ڣ�");
								sb.append(bookPrice);
								sb.append(" ������");
								// ��ȡ�ı��ڵ��ֵ
								sb.append(xrp.nextText());
							}
							sb.append("\n");
						}
						// ��ȡ����������һ���¼�
						xrp.next(); //��
					}
					EditText show = (EditText) findViewById(R.id.show);
					show.setText(sb.toString());
				}
				catch (XmlPullParserException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}