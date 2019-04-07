package org.crazyit.intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
public class ActionAttr extends Activity
{
	public final static String CRAZYIT_ACTION =
		"org.crazyit.intent.action.CRAZYIT_ACTION";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button bn = (Button) findViewById(R.id.bn);
		// Ϊbn��ť���¼�������
		bn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				// ����Intent����
				Intent intent = new Intent();
				// ΪIntent����Action���ԣ�����ֵ����һ����ͨ�ַ�����
				intent.setAction(ActionAttr.CRAZYIT_ACTION);
				startActivity(intent);
			}
		});
	}
}