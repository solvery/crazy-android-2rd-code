package org.crazyit.ui;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Description:
 * <br/>website: <a href="http://www.crazyit.org">crazyit.org</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class BaseAdapterTest extends Activity
{
	ListView myList;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		myList = (ListView) findViewById(R.id.myList);
		BaseAdapter adapter = new BaseAdapter()
		{
			@Override
			public int getCount()
			{
				// ָ��һ������40��ѡ��
				return 40;
			}
			@Override
			public Object getItem(int position)
			{
				return null;
			}
			// ��д�÷������÷����ķ���ֵ����Ϊ�б����ID
			@Override
			public long getItemId(int position)
			{
				return position;
			}
			// ��д�÷������÷������ص�View����Ϊ�б��
			@Override
			public View getView(int position
					, View convertView , ViewGroup parent)
			{
				// ����һ��LinearLayout�������������2�����
				LinearLayout line = new LinearLayout(BaseAdapterTest.this);
				line.setOrientation(0);
				ImageView image = new ImageView(BaseAdapterTest.this);
				image.setImageResource(R.drawable.ic_launcher);
				TextView text = new TextView(BaseAdapterTest.this);
				text.setText("��" + (position +1 ) + "���б���");
				text.setTextSize(20);
				text.setTextColor(Color.RED);
				line.addView(image);
				line.addView(text);
				// ����LinearLayoutʵ��
				return line;
			}
		};
		myList.setAdapter(adapter);
	}
}
