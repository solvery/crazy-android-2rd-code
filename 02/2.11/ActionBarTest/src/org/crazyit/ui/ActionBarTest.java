package org.crazyit.ui;

import android.os.Bundle;
import android.view.View;
import android.app.ActionBar;
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
public class ActionBarTest extends Activity
{
	ActionBar actionBar;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 获取该Activity的ActionBar，
		// 只有当应用主题没有关闭ActionBar时，该代码才能返回ActionBar
		actionBar = getActionBar();
	}
	// 为“显示ActionBar”按钮定义事件处理方法
	public void showActionBar(View source)
	{
		// 显示ActionBar
		actionBar.show();
	}
	// 为“隐藏ActionBar”按钮定义事件处理方法	
	public void hideActionBar(View source)
	{
		// 隐藏ActionBar
		actionBar.hide();
	}
}
