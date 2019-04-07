package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;
import android.content.Intent;
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
public class ManageKind extends FragmentActivity
	implements Callbacks
{
	@Override
	public Fragment getFragment()
	{
		return new ManageKindFragment();
	}	
	@Override
	public void onItemSelected(Integer id, Bundle bundle)
	{
		// 当用户单击ManageKindFragment中添加按钮时，系统启动AddKind Activity
		Intent i = new Intent(this , AddKind.class);
		startActivity(i);
	}
}
