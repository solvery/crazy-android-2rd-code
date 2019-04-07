package org.crazyit.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
public class SpinnerTest extends Activity
{
	Spinner spinner;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取界面布局文件中的Spinner组件
		spinner = (Spinner) findViewById(R.id.spinner);
		String[] arr = { "孙悟空", "猪八戒", "唐僧" };
		// 创建ArrayAdapter对象
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			android.R.layout.simple_list_item_multiple_choice, arr);
		// 为Spinner设置Adapter
		spinner.setAdapter(adapter);
	}
}