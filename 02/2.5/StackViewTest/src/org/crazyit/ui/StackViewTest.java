package org.crazyit.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.StackView;

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
public class StackViewTest extends Activity
{
	StackView stackView;
	int[] imageIds = new int[]
	{
		R.drawable.bomb5 , R.drawable.bomb6 , R.drawable.bomb7
		, R.drawable.bomb8 , R.drawable.bomb9 , R.drawable.bomb10
		, R.drawable.bomb11 , R.drawable.bomb12	, R.drawable.bomb13
		, R.drawable.bomb14 , R.drawable.bomb15 , R.drawable.bomb16
	};
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		stackView = (StackView) findViewById(R.id.mStackView);
		// ����һ��List����List�����Ԫ����Map
		List<Map<String, Object>> listItems = 
				new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imageIds.length; i++)
		{
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", imageIds[i]);
			listItems.add(listItem);
		}
		// ����һ��SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,
				listItems
				// ʹ��/layout/cell.xml�ļ���Ϊ���沼��
				, R.layout.cell, new String[] { "image" },
				new int[] { R.id.image1 });		
		stackView.setAdapter(simpleAdapter);
	
	}
	public void prev(View view)
	{
		// ��ʾ��һ�����
		stackView.showPrevious();
	}
	public void next(View view)
	{
		// ��ʾ��һ�����
		stackView.showNext();
	}
}