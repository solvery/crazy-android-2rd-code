/**
 *
 */
package org.crazyit.auction.client;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Description:
 * <br/>利嫋: <a href="http://www.crazyit.org">決髄Java選男</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class HomeListener implements OnClickListener
{
	private Activity activity;
	public HomeListener(Activity activity)
	{
		this.activity = activity;
	}
	@Override
	public void onClick(View source)
	{
		Intent i = new Intent(activity , AuctionClientActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		activity.startActivity(i);
	}
}
