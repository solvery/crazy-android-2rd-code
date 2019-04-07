package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
public class CodeView extends Activity
{
	// ����һ�δ�����Activityʱ�ص��÷���
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// ����һ�����Բ��ֹ�����
		LinearLayout layout = new LinearLayout(this);
		// ���ø�Activity��ʾlayout
		super.setContentView(layout);
		layout.setOrientation(LinearLayout.VERTICAL);
		// ����һ��TextView
		final TextView show = new TextView(this);
		// ����һ����ť
		Button bn = new Button(this);
		bn.setText(R.string.ok);
		bn.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		// ��Layout���������TextView
		layout.addView(show);
		// ��Layout��������Ӱ�ť
		layout.addView(bn);
		// Ϊ��ť��һ���¼�������
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				show.setText("Hello , Android , " + new java.util.Date());
			}
		});
	}
}