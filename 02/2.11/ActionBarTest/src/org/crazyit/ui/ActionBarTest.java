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
		// ��ȡ��Activity��ActionBar��
		// ֻ�е�Ӧ������û�йر�ActionBarʱ���ô�����ܷ���ActionBar
		actionBar = getActionBar();
	}
	// Ϊ����ʾActionBar����ť�����¼�������
	public void showActionBar(View source)
	{
		// ��ʾActionBar
		actionBar.show();
	}
	// Ϊ������ActionBar����ť�����¼�������	
	public void hideActionBar(View source)
	{
		// ����ActionBar
		actionBar.hide();
	}
}
