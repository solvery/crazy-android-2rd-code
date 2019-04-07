package org.crazyit.event;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
public class EventQs extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡӦ�ó����е�bn��ť
		Button bn = (Button) findViewById(R.id.bn);
		// Ϊ��ť���¼���������
		bn.setOnClickListener(new MyClickListener()); // ��
	}

	// ����һ�������¼��ļ�����
	class MyClickListener implements View.OnClickListener
	{
		// ʵ�ּ����������ʵ�ֵķ������÷���������Ϊ�¼�������
		@Override
		public void onClick(View v)
		{
			EditText txt = (EditText) findViewById(R.id.txt);
			txt.setText("bn��ť�������ˣ�");
		}
	}
}