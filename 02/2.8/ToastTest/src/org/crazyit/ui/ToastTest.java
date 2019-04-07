package org.crazyit.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class ToastTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button simple = (Button) findViewById(R.id.simple);
		// Ϊ��ť�ĵ����¼����¼�������
		simple.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ����һ��Toast��ʾ��Ϣ
				Toast toast = Toast.makeText(ToastTest.this
					, "�򵥵���ʾ��Ϣ"
				// ���ø�Toast��ʾ��Ϣ�ĳ���ʱ��
						, Toast.LENGTH_SHORT);
				toast.show();
			}
		});
		Button bn = (Button) findViewById(R.id.bn);
		// Ϊ��ť�ĵ����¼����¼�������
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ����һ��Toast��ʾ��Ϣ
				Toast toast = new Toast(ToastTest.this);
				// ����Toast����ʾλ��
				toast.setGravity(Gravity.CENTER, 0, 0);
				// ����һ��ImageView
				ImageView image = new ImageView(ToastTest.this);
				image.setImageResource(R.drawable.tools);
				// ����һ��LinearLayout����
				LinearLayout ll = new LinearLayout(ToastTest.this);
				// ��LinearLayout�����ͼƬ��ԭ�е�View
				ll.addView(image);
				// ����һ��ImageView
				TextView textView = new TextView(ToastTest.this);
				textView.setText("��ͼƬ����ʾ��");
				// �����ı���������Ĵ�С����ɫ
				textView.setTextSize(30);
				textView.setTextColor(Color.MAGENTA);
				ll.addView(textView);
				// ����Toast��ʾ�Զ���View				
				toast.setView(ll);
				// ����Toast����ʾʱ��
				toast.setDuration(Toast.LENGTH_LONG);
				toast.show();
			}
		});
	}
}