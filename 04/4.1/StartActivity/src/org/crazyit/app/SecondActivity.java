/**
 *
 */
package org.crazyit.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class SecondActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		// ��ȡӦ�ó����е�previous��ť
		Button previous = (Button) findViewById(R.id.previous);
		// ��ȡӦ�ó����е�close��ť
		Button close = (Button) findViewById(R.id.close);
		// Ϊprevious��ť���¼�������
		previous.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��ȡ������ǰActivity����һ��Intent
				Intent intent = new Intent(SecondActivity.this,
						StartActivity.class);
				// ����intent��Ӧ��Activity
				startActivity(intent);
			}
		});
		// Ϊclose��ť���¼�������
		close.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				// ��ȡ������ǰActivity����һ��Intent
				Intent intent = new Intent(SecondActivity.this,
						StartActivity.class);
				// ����intent��Ӧ��Activity
				startActivity(intent);
				// ������ǰActivity
				finish();
			}
		});
	}
}
