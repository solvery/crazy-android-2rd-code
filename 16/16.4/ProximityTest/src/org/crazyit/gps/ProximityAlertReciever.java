/**
 *
 */
package org.crazyit.gps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
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
public class ProximityAlertReciever extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		// ��ȡ�Ƿ�Ϊ����ָ������
		boolean isEnter = intent.getBooleanExtra(
			LocationManager.KEY_PROXIMITY_ENTERING, false);
		if(isEnter)
		{
			// ��ʾ��ʾ��Ϣ
			Toast.makeText(context
				, "���Ѿ�������������"
				, Toast.LENGTH_LONG)
				.show();
		}
		else
		{
			// ��ʾ��ʾ��Ϣ
			Toast.makeText(context
				, "���Ѿ��뿪���������"
				, Toast.LENGTH_LONG)
				.show();
		}
	}
}
