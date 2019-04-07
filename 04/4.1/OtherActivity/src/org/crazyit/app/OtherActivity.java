package org.crazyit.app;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

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
public class OtherActivity extends LauncherActivity
{
	//��������Activity������
	String[] names = {"���ó������" ,  "�鿴�Ǽʱ���"};
	//��������Activity��Ӧ��ʵ����
	Class<?>[] clazzs = {PreferenceActivityTest.class
		, ExpandableListActivityTest.class};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_1 , names);
		// ���øô�����ʾ���б������Adapter
		setListAdapter(adapter);
	}
	//�����б���������ָ��Activity��Ӧ��Intent
	@Override public Intent intentForPosition(int position)
	{
		return new Intent(OtherActivity.this , clazzs[position]);
	}
}