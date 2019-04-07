package org.crazyit.ui;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

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
public class ActionBar_TabSwipeNav extends FragmentActivity
	implements ActionBar.TabListener
{
	ViewPager viewPager;
	ActionBar actionBar;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// ��ȡActionBar����
		actionBar = getActionBar();
		// ��ȡViewPager
		viewPager = (ViewPager) findViewById(R.id.pager);
		// ����һ��FragmentPagerAdapter���󣬸ö�����ΪViewPager�ṩ���Fragment
		FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(
				getSupportFragmentManager())
		{
			// ��ȡ��positionλ�õ�Fragment
			@Override
			public Fragment getItem(int position)
			{
				Fragment fragment = new DummyFragment();
				Bundle args = new Bundle();
				args.putInt(DummyFragment.ARG_SECTION_NUMBER, position + 1);
				fragment.setArguments(args);
				return fragment;
			}
			// �÷����ķ���ֵi������Adapter�ܹ��������ٸ�Fragment
			@Override
			public int getCount()
			{
				return 3;
			}
			// �÷����ķ���ֵ����ÿ��Fragment�ı���
			@Override
			public CharSequence getPageTitle(int position)
			{
				switch (position)
				{
					case 0:
						return "��һҳ";
					case 1:
						return "�ڶ�ҳ";
					case 2:
						return "����ҳ";
				}
				return null;
			}
		};
		// ����ActionBarʹ��Tab������ʽ
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// ����pagerAdapter������������ȫ��Fragment��
		// ÿ��Fragment��Ӧ����һ��Tab��ǩ
		for (int i = 0; i < pagerAdapter.getCount(); i++)
		{
			actionBar.addTab(actionBar.newTab()
				.setText(pagerAdapter.getPageTitle(i))
				.setTabListener(this));
		}
		// ΪViewPager�������FragmentPagerAdapter
		viewPager.setAdapter(pagerAdapter);  //��
		// ΪViewPager������¼�������
		viewPager.setOnPageChangeListener(
			new ViewPager.SimpleOnPageChangeListener()
			{
				// ��ViewPager��ʾ��Fragment�����ı�ʱ�����÷���
				@Override
				public void onPageSelected(int position)
				{
					actionBar.setSelectedNavigationItem(position);
				}
			});
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
		viewPager.setCurrentItem(tab.getPosition());  //��
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction)
	{
	}
}
