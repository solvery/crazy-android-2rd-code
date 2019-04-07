package org.crazyit.res;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
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
public class ArrayResTest extends Activity
{
	// ��ȡϵͳ�����������Դ
	String[] texts;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		texts = getResources().getStringArray(R.array.string_arr);
		// ����һ��BaseAdapter����
		BaseAdapter ba = new BaseAdapter()
		{
			@Override
			public int getCount()
			{
				// ָ��һ������9��ѡ��
				return texts.length;
			}

			@Override
			public Object getItem(int position)
			{
				// ����ָ��λ�õ��ı�
				return texts[position];
			}

			@Override
			public long getItemId(int position)
			{
				return position;
			}

			// ��д�÷������÷������ص�View����Ϊ��GridView��ÿ������
			@Override
			public View getView(int position
					, View convertView, ViewGroup parent)
			{
				TextView text = new TextView(ArrayResTest.this);
				Resources res = ArrayResTest.this.getResources();
				// ʹ�ó߶���Դ�������ı���ĸ߶ȡ����
				text.setWidth((int) res.getDimension(R.dimen.cell_width));
				text.setHeight((int) res.getDimension(R.dimen.cell_height));
				// ʹ���ַ�����Դ�����ı��������
				text.setText(texts[position]);
				TypedArray icons = res.obtainTypedArray(R.array.plain_arr);
				// ʹ����ɫ��Դ�������ı���ı���ɫ
				text.setBackgroundDrawable(icons.getDrawable(position));
				text.setTextSize(20);
				return text;
			}
		};
		GridView grid = (GridView) findViewById(R.id.grid01);
		// ΪGridView����Adapter
		grid.setAdapter(ba);
	}
}