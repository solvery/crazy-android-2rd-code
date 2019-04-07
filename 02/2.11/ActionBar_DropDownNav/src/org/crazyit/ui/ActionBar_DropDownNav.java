package org.crazyit.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.ArrayAdapter;

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
public class ActionBar_DropDownNav extends Activity implements
		ActionBar.OnNavigationListener
{
	private static final String SELECTED_ITEM = "selected_item";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ActionBar actionBar = getActionBar();
		// ����ActionBar�Ƿ���ʾ����
		actionBar.setDisplayShowTitleEnabled(true);
		// ���õ���ģʽ,ʹ��List����
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		// ΪactionBar��װArrayAdapter
		actionBar.setListNavigationCallbacks(
			new ArrayAdapter<String>(ActionBar_DropDownNav.this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, new String[]
				{"��һҳ","�ڶ�ҳ","����ҳ" }), this);
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
	// �������ѡ��ʱ�����÷���
	@Override
	public boolean onNavigationItemSelected(int position, long id)
	{
		// ����һ���µ�Fragment����
		Fragment fragment = new DummyFragment();
		// ����һ��Bundle����������Fragment�������
		Bundle args = new Bundle();
		args.putInt(DummyFragment.ARG_SECTION_NUMBER, position + 1);
		// ��fragment�������
		fragment.setArguments(args);
		// ��ȡFragmentTransaction����
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		// ʹ��fragment�����Activity�е�container���
		ft.replace(R.id.container, fragment);
		// �ύ����
		ft.commit();
		return true;
	}
}
