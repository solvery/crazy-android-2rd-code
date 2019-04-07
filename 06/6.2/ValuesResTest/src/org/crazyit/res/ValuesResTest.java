package org.crazyit.res;

import android.app.Activity;
import android.content.res.Resources;
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
public class ValuesResTest extends Activity
{
	// ʹ���ַ�����Դ
	int[] textIds = new int[]
	{
		R.string.c1 , R.string.c2 , R.string.c3 ,
		R.string.c4 , R.string.c5 , R.string.c6 ,
		R.string.c7 , R.string.c8 , R.string.c9
	};
	// ʹ����ɫ��Դ
	int[] colorIds = new int[]
	{
		R.color.c1 , R.color.c2 , R.color.c3 ,
		R.color.c4 , R.color.c5 , R.color.c6 ,
		R.color.c7 , R.color.c8 , R.color.c9
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ����һ��BaseAdapter����
		BaseAdapter ba = new BaseAdapter()
		{
			@Override
			public int getCount()
			{
				// ָ��һ������9��ѡ��
				return textIds.length;
			}

			@Override
			public Object getItem(int position)
			{
				// ����ָ��λ�õ��ı�
				return getResources().getText(textIds[position]);
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
				TextView text = new TextView(ValuesResTest.this);
				Resources res = ValuesResTest.this.getResources();
				// ʹ�ó߶���Դ�������ı���ĸ߶ȡ����
				text.setWidth((int) res.getDimension(R.dimen.cell_width));
				text.setHeight((int) res.getDimension(R.dimen.cell_height));
				// ʹ���ַ�����Դ�����ı��������
				text.setText(textIds[position]);
				// ʹ����ɫ��Դ�������ı���ı���ɫ
				text.setBackgroundResource(colorIds[position]);
				text.setTextSize(20);
				text.setTextSize(getResources()
					.getInteger(R.integer.font_size));
				return text;
			}
		};
		GridView grid = (GridView)findViewById(R.id.grid01);
		// ΪGridView����Adapter
		grid.setAdapter(ba);
	}
}