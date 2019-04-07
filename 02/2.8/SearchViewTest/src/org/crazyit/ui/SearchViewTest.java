package org.crazyit.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.app.Activity;

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
public class SearchViewTest extends Activity implements
		SearchView.OnQueryTextListener
{
	private SearchView sv;
	private ListView lv;
	// 自动完成的列表
	private final String[] mStrings = { "aaaaa", "bbbbbb", "cccccc" };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mStrings));
		lv.setTextFilterEnabled(true);
		sv = (SearchView) findViewById(R.id.sv);
		// 设置该SearchView默认是否自动缩小为图标
		sv.setIconifiedByDefault(false);
		// 为该SearchView组件设置事件监听器
		sv.setOnQueryTextListener(this);
		// 设置该SearchView显示搜索按钮
		sv.setSubmitButtonEnabled(true);
		// 设置该SearchView内默认显示的提示文本
		sv.setQueryHint("查找");
	}

	// 用户输入字符时激发该方法
	@Override
	public boolean onQueryTextChange(String newText)
	{
		if (TextUtils.isEmpty(newText))
		{
			// 清除ListView的过滤
			lv.clearTextFilter();
		}
		else
		{
			// 使用用户输入的内容对ListView的列表项进行过滤
			lv.setFilterText(newText);
		}
		return true;
	}

	// 单击搜索按钮时激发该方法
	@Override
	public boolean onQueryTextSubmit(String query)
	{
		// 实际应用中应该在该方法内执行实际查询
		// 此处仅使用Toast显示用户输入的查询内容
		Toast.makeText(this, "您的选择是:" + query
				, Toast.LENGTH_SHORT).show();
		return false;
	}
}
