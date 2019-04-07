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
public class BatteryReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Bundle bundle = intent.getExtras();
		// ��ȡ��ǰ����
		int current = bundle.getInt("level");
		// ��ȡ�ܵ���
		int total = bundle.getInt("scale");
		// �����ǰ����С���ܵ�����15%
		if (current * 1.0 / total < 0.15)
		{
			Toast.makeText(context, "�������ͣ��뾡���磡"
				, Toast.LENGTH_LONG).show();
		}
	}
}