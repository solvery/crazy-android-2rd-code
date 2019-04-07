package org.crazyit.other;

import org.crazyit.other.R;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
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
public class ReadOtherPreferences extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Context useCount = null;
		try
		{
			// ��ȡ������������Ӧ��Context
			useCount = createPackageContext("org.crazyit.io",
				Context.CONTEXT_IGNORE_SECURITY);
		}
		catch (NameNotFoundException e)
		{
			e.printStackTrace();
		}
		// ʹ�����������Context��ȡ��Ӧ��SharedPreferences
		SharedPreferences prefs = useCount.getSharedPreferences("count",
			Context.MODE_WORLD_READABLE);
		// ��ȡ����
		int count = prefs.getInt("count", 0);
		TextView show = (TextView) findViewById(R.id.show);
		// ��ʾ��ȡ����������
		show.setText("UseCountӦ�ó�����ǰ��ʹ����" + count + "�Ρ�");
	}
}