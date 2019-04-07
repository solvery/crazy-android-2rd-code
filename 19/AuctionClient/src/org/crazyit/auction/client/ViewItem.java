package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class ViewItem extends FragmentActivity
{
	// 重写getFragment()方法，该Activity显示该方法返回的Fragment
	@Override
	protected Fragment getFragment()
	{
		ViewItemFragment fragment = new ViewItemFragment();
		Bundle arguments = new Bundle();
		arguments.putString("action"
			, getIntent().getStringExtra("action"));
		fragment.setArguments(arguments);
		return fragment;
	}
}
