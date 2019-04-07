package org.crazyit.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * Description: <br/>
 * website: <a href="http://www.crazyit.org">crazyit.org</a> <br/>
 * Copyright (C), 2001-2014, Yeeku.H.Lee <br/>
 * This program is protected by copyright laws. <br/>
 * Program Name: <br/>
 * Date:
 * 
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class ViewSwitcherTest extends Activity
{
	// ����һ��������������ʾÿ����ʾ��Ӧ�ó�����
	public static final int NUMBER_PER_SCREEN = 12;

	// ����Ӧ�ó�����ڲ��࣬
	public static class DataItem
	{
		// Ӧ�ó�������
		public String dataName;
		// Ӧ�ó���ͼ��
		public Drawable drawable;
	}

	// ����ϵͳ����Ӧ�ó����List����
	private ArrayList<DataItem> items = new ArrayList<DataItem>();
	// ��¼��ǰ������ʾ�ڼ����ĳ���
	private int screenNo = -1;
	// ���������ռ��������
	private int screenCount;
	ViewSwitcher switcher;
	// ����LayoutInflater����
	LayoutInflater inflater;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		inflater = LayoutInflater.from(ViewSwitcherTest.this);
		// ����һ������40��Ԫ�ص�List���ϣ�����ģ�����40��Ӧ�ó���
		for (int i = 0; i < 40; i++)
		{
			String label = "" + i;
			Drawable drawable = getResources().getDrawable(
					R.drawable.ic_launcher);
			DataItem item = new DataItem();
			item.dataName = label;
			item.drawable = drawable;
			items.add(item);
		}
		// ����Ӧ�ó�����ռ����������
		// ���Ӧ�ó��������������NUMBER_PER_SCREEN�������Ľ��������������
		// �������������������Ӧ���ǳ����Ľ���ټ�1��
		screenCount = items.size() % NUMBER_PER_SCREEN == 0 ? 
				items.size()/ NUMBER_PER_SCREEN :
				items.size() / NUMBER_PER_SCREEN	+ 1;
		switcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
		switcher.setFactory(new ViewFactory()
		{
			// ʵ���Ͼ��Ƿ���һ��GridView���
			@Override
			public View makeView()
			{
				// ����R.layout.slidelistview�����ʵ���Ͼ���һ��GridView�����
				return inflater.inflate(R.layout.slidelistview, null);
			}
		});
		// ҳ�����ʱ����ʾ��һ����
		next(null);
	}

	public void next(View v)
	{
		if (screenNo < screenCount - 1)
		{
			screenNo++;
			// ΪViewSwitcher�������ʾ�������ö���
			switcher.setInAnimation(this, R.anim.slide_in_right);
			// ΪViewSwitcher��������ع������ö���
			switcher.setOutAnimation(this, R.anim.slide_out_left);
			// ������һ����Ҫ��ʾ��GridView��Ӧ�� Adapter
			((GridView) switcher.getNextView()).setAdapter(adapter);
			// ����ұ߰�ť����ʾ��һ����
			// ѧϰ���Ƽ���Ҳ��ͨ�����Ƽ��ʵ����ʾ��һ��.
			switcher.showNext();  // ��
		}
	}

	public void prev(View v)
	{
		if (screenNo > 0)
		{
			screenNo--;
			// ΪViewSwitcher�������ʾ�������ö���
			switcher.setInAnimation(this, android.R.anim.slide_in_left);
			// ΪViewSwitcher��������ع������ö���
			switcher.setOutAnimation(this, android.R.anim.slide_out_right);
			// ������һ����Ҫ��ʾ��GridView��Ӧ�� Adapter
			((GridView) switcher.getNextView()).setAdapter(adapter);
			// �����߰�ť����ʾ��һ������Ȼ���Բ�������
			// ѧϰ���Ƽ���Ҳ��ͨ�����Ƽ��ʵ����ʾ��һ��.
			switcher.showPrevious();   // ��
		}
	}

	// ��BaseAdapter����Ϊÿ����ʾ��GridView�ṩ�б���
	private BaseAdapter adapter = new BaseAdapter()
	{
		@Override
		public int getCount()
		{
			// ����Ѿ��������һ������Ӧ�ó����������������NUMBER_PER_SCREEN
			if (screenNo == screenCount - 1
					&& items.size() % NUMBER_PER_SCREEN != 0)
			{
				// ���һ����ʾ�ĳ�����ΪӦ�ó����������NUMBER_PER_SCREEN����
				return items.size() % NUMBER_PER_SCREEN;
			}
			// ����ÿ����ʾ�ĳ�������ΪNUMBER_PER_SCREEN
			return NUMBER_PER_SCREEN;
		}

		@Override
		public DataItem getItem(int position)
		{
			// ����screenNo�����position���б��������
			return items.get(screenNo * NUMBER_PER_SCREEN + position);
		}

		@Override
		public long getItemId(int position)
		{
			return position;
		}

		@Override
		public View getView(int position
				, View convertView, ViewGroup parent)
		{
			View view = convertView;
			if (convertView == null)
			{
				// ����R.layout.labelicon�����ļ�
				view = inflater.inflate(R.layout.labelicon, null);
			}
			// ��ȡR.layout.labelicon�����ļ��е�ImageView�������Ϊ֮����ͼ��
			ImageView imageView = (ImageView)
					view.findViewById(R.id.imageview);
			imageView.setImageDrawable(getItem(position).drawable);
			// ��ȡR.layout.labelicon�����ļ��е�TextView�������Ϊ֮�����ı�
			TextView textView = (TextView) 
					view.findViewById(R.id.textview);
			textView.setText(getItem(position).dataName);
			return view;
		}
	};
}