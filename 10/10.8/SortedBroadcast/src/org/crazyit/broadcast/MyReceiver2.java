/**
 *
 */
package org.crazyit.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2014, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class MyReceiver2 extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = getResultExtras(true);
		// ����ǰһ��BroadcastReceiver�������keyΪfirst����Ϣ
		String first = bundle.getString("first");
		Toast.makeText(context, "��һ��Broadcast�������ϢΪ��" 
			+ first, Toast.LENGTH_LONG).show();
	}
}
