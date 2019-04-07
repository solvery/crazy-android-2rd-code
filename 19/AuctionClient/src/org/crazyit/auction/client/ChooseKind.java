package org.crazyit.auction.client;

import org.crazyit.app.base.FragmentActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class ChooseKind extends FragmentActivity
	implements Callbacks
{
	@Override
	public Fragment getFragment()
	{
		return new ChooseKindFragment();
	}
	@Override
	public void onItemSelected(Integer id, Bundle bundle)
	{
		Intent intent = new Intent(this , ChooseItem.class);
		intent.putExtra("kindId", bundle.getLong("kindId"));
		startActivity(intent);
	}
}
