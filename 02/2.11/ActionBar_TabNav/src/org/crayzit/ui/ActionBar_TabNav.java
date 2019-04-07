package org.crayzit.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

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
public class ActionBar_TabNav extends Activity implements
	ActionBar.TabListener
{
	private static final String SELECTED_ITEM = "selected_item";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ActionBar actionBar = getActionBar();
		// ����ActionBar�ĵ�����ʽ��Tab����
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// �������3��Tabҳ����Ϊ3��Tab��ǩ����¼�������
		actionBar.addTab(actionBar.newTab().setText("��һҳ")
			.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("�ڶ�ҳ")
			.setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("����ҳ")
			.setTabListener(this));
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		if (savedInstanceState.containsKey(SELECTED_ITEM))
		{
			// ѡ��ǰ�汣���������Ӧ��Fragmentҳ
			getActionBar().setSelectedNavigationItem(
				savedInstanceState.getInt(SELECTED_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		// ����ǰѡ�е�Fragmentҳ���������浽Bundle��
		outState.putInt(SELECTED_ITEM, 
			getActionBar().getSelectedNavigationIndex());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
		FragmentTransaction fragmentTransaction)
	{
	}
	// ��ָ��Tab��ѡ��ʱ�����÷���
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction)
	{
		// ����һ���µ�Fragment����
		Fragment fragment = new DummyFragment();
		// ����һ��Bundle����������Fragment�������
		Bundle args = new Bundle();
		args.putInt(DummyFragment.ARG_SECTION_NUMBER,
				tab.getPosition() + 1);
		// ��fragment�������
		fragment.setArguments(args);
		// ��ȡFragmentTransaction����
		FragmentTransaction ft = getFragmentManager()
				.beginTransaction();
		// ʹ��fragment�����Activity�е�container���
		ft.replace(R.id.container, fragment);
		// �ύ����
		ft.commit();
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction)
	{
	}
}
